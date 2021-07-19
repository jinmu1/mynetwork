package com.ruoyi.network.service;

import java.util.List;
import com.ruoyi.system.domain.GlcNetworkSupplyChain;

/**
 * glcService接口
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
public interface IGlcNetworkSupplyChainService 
{
    /**
     * 查询glc
     * 
     * @param id glcID
     * @return glc
     */
    public GlcNetworkSupplyChain selectGlcNetworkSupplyChainById(Long id);

    /**
     * 查询glc列表
     * 
     * @param glcNetworkSupplyChain glc
     * @return glc集合
     */
    public List<GlcNetworkSupplyChain> selectGlcNetworkSupplyChainList(GlcNetworkSupplyChain glcNetworkSupplyChain);

    /**
     * 新增glc
     * 
     * @param glcNetworkSupplyChain glc
     * @return 结果
     */
    public int insertGlcNetworkSupplyChain(GlcNetworkSupplyChain glcNetworkSupplyChain);

    /**
     * 修改glc
     * 
     * @param glcNetworkSupplyChain glc
     * @return 结果
     */
    public int updateGlcNetworkSupplyChain(GlcNetworkSupplyChain glcNetworkSupplyChain);

    /**
     * 批量删除glc
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlcNetworkSupplyChainByIds(String ids);

    /**
     * 删除glc信息
     * 
     * @param id glcID
     * @return 结果
     */
    public int deleteGlcNetworkSupplyChainById(Long id);

    int deleteGlcNetworkSupplyChainByUserId(Long userId);

    String importExcel(List<GlcNetworkSupplyChain> propertyList, boolean updateSupport, Long userId);
}
