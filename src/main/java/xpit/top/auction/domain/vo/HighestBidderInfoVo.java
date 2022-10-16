package xpit.top.auction.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author Pu Tongjiao
 * @date 2022/9/28 20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighestBidderInfoVo {
    private String highestBidder;
    private BigInteger highestBid;
    private BigInteger secondHighestBid;
}
