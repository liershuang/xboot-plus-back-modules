package cn.exrick.xboot.xiaomu.common.enums;

public enum TreeType {

    CODE_GROUP_TREE(1,"代码模板组树");

    private Integer value;
    private String desc;


    TreeType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }

}
