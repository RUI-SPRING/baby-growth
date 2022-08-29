package com.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ExtraUser实体类
 * createBy：NanMing
 * createTime：2022/8/29
 */
@Data
@Builder
@TableName(value = "extra_user")//指定表名
@ApiModel(value="ExtraUser",description="用户user的额外属性")
public class ExtraUser implements Serializable {

    //指定自增策略
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "用户额外属性表主键id",required = true)
    private Long id;

    @ApiModelProperty(value = "用户唯一id")
    private Long userId;

    @ApiModelProperty(value = "宝宝相册id集合")
    private List<Long> babyPhoneAlbumIds;

    @ApiModelProperty(value = "奶爸任务id集合")
    private List<Long> daddyTaskIds;

    @ApiModelProperty(value = "宝宝成长记录id集合")
    private List<Long> babyGrowthRecordIds;

    @ApiModelProperty(value = "育儿交流id集合")
    private List<Long> parentingCommunicationIds;

    @ApiModelProperty(value = "晒娃图片id集合")
    private List<Long> flauntPhotoIds;

    @ApiModelProperty(value = "宝宝学习记录id集合")
    private List<Long> babyLearningRecordIds;

    @ApiModelProperty(value = "系统消息id集合")
    private List<Long> messageIds;

    @ApiModelProperty(value = "扩展字段")
    private String extend;


}
