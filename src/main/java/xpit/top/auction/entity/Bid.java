package xpit.top.auction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author Pu Tongjiao
 * @date 2022/9/28 18:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bid {

    private BigInteger productId;
    private String amount;
    private String secret;
    private BigInteger weiValue;
}
