/**
 * Copyright (C), 2019, 小木
 * FileName: BaseEntity
 * Author:   xiaomu
 * Date:     2019/10/21 23:56
 * Description:
 * History:
 */
package cn.exrick.xboot.xiaomu.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    private Integer id;

    private Date createDatetime;

    private Date updateDatetime;

    private String createBy;

    private String updateBy;



}
