package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.GlcNetworkSupplyChain;

/**
 * glcMapper接口
 * 
 * @author ruoyi
 * @date 2021-01-27
 */
public interface GlcNetworkSupplyChainMapper 
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
     * 删除glc
     * 
     * @param id glcID
     * @return 结果
     */
    public int deleteGlcNetworkSupplyChainById(Long id);

    /**
     * 批量删除glc
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGlcNetworkSupplyChainByIds(String[] ids);

    int deleteGlcNetworkSupplyChainByBatchId(Long userId);

    void insertGlcNetworkSupplyChainList(List<GlcNetworkSupplyChain> list);
}
