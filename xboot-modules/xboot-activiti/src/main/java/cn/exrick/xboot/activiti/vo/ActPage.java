package cn.exrick.xboot.activiti.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Exrickx
 */
@Data
public class ActPage<T> {

    List<T> content;

    Long totalElements;
}
