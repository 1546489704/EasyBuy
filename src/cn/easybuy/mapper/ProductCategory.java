package cn.easybuy.mapper;

import cn.easybuy.param.ProductCategoryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategory {
    /**
     * 根据id删除商品
     * @param parseLong
     */
    void deleteById(@Param("parseLong") Integer parseLong);//删除商品分类
    /**
     * 根据条件查询商品列表
     * @param param
     */
    public List<cn.easybuy.entity.ProductCategory> queryProductCategorylist(ProductCategoryParam param);
    /**
     * 根据id查询商品分类
     * @param
     */
    public cn.easybuy.entity.ProductCategory queryProductCategoryById(Integer id);

    /**
     * 添加商品分类
     * @param productCategory
     * @return
     */
    public Integer add(cn.easybuy.entity.ProductCategory productCategory) ;
    /**
     * 根据参数查询商品分类的数目
     * @param
     */
    public Integer queryProductCategoryCount(ProductCategoryParam param);
    /**
     * 修改商品分类
     * @param
     */
    public void update(cn.easybuy.entity.ProductCategory productCategory) ;
}
