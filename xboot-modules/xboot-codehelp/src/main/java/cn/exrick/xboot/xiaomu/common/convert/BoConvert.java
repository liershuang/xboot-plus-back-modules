/**
 * Copyright (C), 2019, 小木
 * FileName: BoConvert
 * Author:   xiaomu
 * Date:     2019/10/25 18:28
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.common.convert;

import java.util.List;

public interface BoConvert<S, T> {

    T convert(S s);

    List<T> convert(List<S> sList);


}
