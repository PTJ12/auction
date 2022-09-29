package xpit.top.action.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pu Tongjiao
 * @date 2022/9/29 17:21
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    private String tokenHeader;

    private String tokenHead;

    private String token;
}
