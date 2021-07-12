package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GlcNetworkTransportProperty;

/**
 * 运输属性数据Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
public interface GlcNetworkTransportPropertyMapper 
{
    /**
     * 查询运输属性数据
     * 
     * @param id 运输属性数据ID
     * @return 运输属性数据
     */
    public GlcNetworkTransportProperty selectGlcNetworkTransportPropertyById(Long id);

    /**
     * 查询运输属性数据列表
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 运输属性数据集合
     */
    public List<GlcNetworkTransportProperty> selectGlcNetworkTransportPropertyList(GlcNetworkTransportProperty glcNetworkTransportProperty);

    /**
     * 新增运输属性数据
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 结果
     */
    public int insertGlcNetworkTransportProperty(GlcNetworkTransportProperty glcNetworkTransportProperty);

    /**
     * 修改运输属性数据
     * 
     * @param glcNetworkTransportProperty 运输属性数据
     * @return 结果
     */
    public int updateGlcNetworkTransportProperty(GlcNetworkTransportProperty glcNetworkTransportProperty);

    /**
     * 删除运输属性数据
     * 
     * @param id 运输属性数据ID
     * @return 结果
     */
    public int deleteGlcNetworkTransportPropertyById(Long id);

    /**
     * 批量删除运输属性数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlcNetworkTransportPropertyByIds(String[] ids);

    int deleteNetworkTransportByUserId(Long userId);

    void insertGlcNetworkTransportPropertyList(List<GlcNetworkTransportProperty> list);
}
