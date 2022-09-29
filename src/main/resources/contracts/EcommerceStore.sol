// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.4.22 <0.9.0;

import "contracts/Escrow.sol";

contract EcommerceStore{
    // 存储的状态
    enum ProductStatus { Open, Sold, Unsold }
    // 存储的状况 新品或二手
    enum ProductCondition { New, Used }
    // index
    uint productIndex;
    // 产品id->创建者
    mapping (uint => address) productIdInStore;
    // 商家->产品
    mapping (address => mapping(uint => Product)) stores;
    // 竞价人->产品
    mapping (address => mapping (bytes32 => Bid)) bids;
    mapping (uint => address) productEscrow;

    struct Product {
        uint id; //id
        string name; //名称
        string category; //分类
        string imageLink; //图片
        string descLink; //描述
        uint auctionStartTime; // 竞拍开始时间
        uint auctionEndTime; // 竞拍结束时间
        uint startPrice; // 价格
        address highestBidder; // 竞拍者
        uint highestBid; // 竞拍价格
        uint secondHightestBid; // 第二高的价格
        uint totalBids; // 商品总的竞价次数
        ProductStatus status; // 存储的状态
        ProductCondition condition; // 存储的状况
        mapping (address => mapping (bytes32 => Bid)) bids; //竞价人->产品
    }

    struct Bid {
        address bidder; // 竞价人
        uint productId; // 产品
        uint value; // 押金
        bool revealed; //是否揭示 默认false
    }

    constructor() public {
        productIndex = 0;
    }
    // 添加产品
    function addProductToStore(string _name,
        string _category,
        string _imageLink,
        string _descLink,
        uint _auctionStartTime,
        uint _auctionEndTime,
        uint _startPrice,
        uint _productCondition) public {
        // 起拍时间小于结束时间
        require(_auctionStartTime < _auctionEndTime, "Aution start time should be earlier than end time");
        //商品计数器+1
        productIndex += 1;
        Product memory product = Product(productIndex, _name, _category, _imageLink, _descLink, _auctionStartTime, _auctionEndTime, _startPrice, address(0), 0, 0, 0, ProductStatus.Open, ProductCondition(_productCondition));
        stores[msg.sender][productIndex] = product;
        productIdInStore[productIndex] = msg.sender;

    }
    // 查询商品
    function getProduct(uint _productId) public view returns(uint, string, string, string, string, uint, uint, uint, ProductStatus, ProductCondition) {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return (product.id,
        product.name,
        product.category,
        product.imageLink,
        product.descLink,
        product.auctionStartTime,
        product.auctionEndTime,
        product.startPrice,
        product.status,
        product.condition);
    }

    // 竞拍
    function bid(uint _productId, string _amount, string _secret) public payable returns (bool){
        Product storage product = stores[productIdInStore[_productId]][_productId];
        bytes32 sealedBid = keccak256(abi.encode(_amount, _secret));
        require(now >= product.auctionStartTime, "Current time should be later than auction start time");
        require(now <= product.auctionEndTime, "Current time should be earlier than auction end time");
        require(msg.value > product.startPrice, "Value should be larger than start price");
        require(product.bids[msg.sender][sealedBid].bidder == 0, "Bidder should be null");
        product.bids[msg.sender][sealedBid] = Bid(msg.sender, _productId, msg.value, false);
        product.totalBids += 1;
        return true;
    }

    // 揭示报价
    function revealBid(uint _productId, string _amount, string _secret) public {
        Product storage product = stores[productIdInStore[_productId]][_productId];
        require(now >= product.auctionEndTime);
        bytes32 sealedBid = keccak256(abi.encode(_amount, _secret));
        // bytes32 sealedBid = sha3(_amount, _secret);
        Bid memory bidInfo = product.bids[msg.sender][sealedBid];
        require(bidInfo.bidder > 0, "Bidder should exist");
        require(bidInfo.revealed == false, "Bid hsould not be revealed");
        uint refund;
        uint amount = stringToUint(_amount);
        if(bidInfo.value < amount){
            refund = bidInfo.value;
        } else {
            if(address(product.highestBidder) == 0){
                product.highestBidder = msg.sender;
                product.highestBid = amount;
                product.secondHightestBid = product.startPrice;
                refund = bidInfo.value - amount;
            } else {
                if(amount > product.highestBid){ //大于最高价
                    product.secondHightestBid = product.highestBid;
                    product.highestBidder.transfer(product.highestBid);
                    product.highestBid = amount;
                    product.highestBidder = msg.sender;
                    refund = bidInfo.value - amount;
                } else if(amount > product.secondHightestBid){ //大于第二报价
                    product.secondHightestBid = amount;
                    refund = bidInfo.value;
                }else{ //小于第二报价
                    refund = bidInfo.value;
                }
            }
        }
        //修改揭示为true
        product.bids[msg.sender][sealedBid].revealed = true;
        //返回以太
        if(refund > 0){
            msg.sender.transfer(refund);
        }
    }

    function stringToUint(string s) private pure returns(uint){
        bytes memory b = bytes(s);
        uint result = 0;
        for(uint i = 0; i < b.length; i++){
            if(b[i] >= 48 && b[i] <= 57){
                result = result * 10 + (uint(b[i]) - 48);
            }
        }
        return result;
    }

    function highestBidderInfo(uint _productId) public view returns(address, uint, uint){
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return (product.highestBidder, product.highestBid, product.secondHightestBid);
    }
    //竞价次数
    function totalBids(uint _productId) public view returns(uint){
        Product memory product = stores[productIdInStore[_productId]][_productId];
        return product.totalBids;
    }
    //竞拍结束
    function finalizeAuction(uint _productId) public {
        Product memory product = stores[productIdInStore[_productId]][_productId];
        require((now > product.auctionEndTime), "Current time should be later than auction end time");
        require(product.status == ProductStatus.Open, "Product status should be open");
        require(msg.sender != productIdInStore[_productId], "Caller should not be seller");
        require(msg.sender != product.highestBidder, "Caller should not be buyer");
        if (product.highestBidder == 0){
            product.status = ProductStatus.Unsold;
        }else{
            Escrow escrow = (new Escrow).value(product.secondHightestBid)(_productId, product.highestBidder, productIdInStore[_productId], msg.sender);
            productEscrow[_productId] = address(escrow);
            product.status = ProductStatus.Sold;
            uint refund = product.highestBid - product.secondHightestBid;
            product.highestBidder.transfer(refund);
        }
        stores[productIdInStore[_productId]][_productId] = product;
    }

    function escrowAddressForProduct(uint _productId) public view returns(address){
        return Escrow(productEscrow[_productId]);
    }

    function escrowInfo(uint _productId) public view returns(address, address, address, bool, uint, uint){
        return Escrow(productEscrow[_productId]).escrowInfo();
    }

    function releaseAmountToSeller(uint _productId) public {
        Escrow(productEscrow[_productId]).releaseAmountToSeller(msg.sender);
    }

    function refundAmountToBuyer(uint _productId) public {
        Escrow(productEscrow[_productId]).refundAmountToBuyer(msg.sender);
    }
}