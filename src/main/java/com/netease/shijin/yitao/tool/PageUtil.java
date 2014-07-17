package com.netease.shijin.yitao.tool;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页函数。<br />
 * 字段列表：页码、页容、记录数、页数、数据集<br />
 * 页容默认是10，也可以设置。<br />
 * 
 * @author dream
 * 
 * @param <T>需要分页的数据集的内部单元的类型
 */
public class PageUtil<T> {

    /**
     * 当前页码
     */
    private int pageNo = 1;

    /**
     * 每页内记录条数（页容）
     */
    private int pagePerNum = 10;

    /**
     * 全部记录数
     */
    private int allNum = 0;

    /**
     * 全部页数
     */
    private int allPage = 0;

    /**
     * 需要分页的记录集合
     */
    private List<T> data;

    /**
     * 构造函数<br />
     * 根据默认的方法进行分页
     * 
     * @param data
     */
    public PageUtil(List<T> data) {
        this.data = data;
        // 初始化数据
        initPageUtil();
    }

    /**
     * 构造函数<br />
     * 根据指定的页容进行分页
     * 
     * @param data
     */
    public PageUtil(List<T> data, int pagePerNum) {
        this.data = data;
        this.pagePerNum = pagePerNum;
        initPageUtil();
    }

    /**
     * 初始化分页类的参数
     * 
     */
    private void initPageUtil() {
        if (data != null) {
            allNum = data.size();
            allPage = (allNum + pagePerNum - 1) / pagePerNum;
        }
    }

    /**
     * 获取当前页码
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 获取每页内记录条数（页容）
     * @return
     */
    public int getPagePerNum() {
        return pagePerNum;
    }

    /**
     * 获取全部记录数
     * @return
     */
    public int getAllNum() {
        return allNum;
    }

    /**
     * 获取全部页数
     * @return
     */
    public int getAllPage() {
        return allPage;
    }

    /**
     * 分页主方法，获取分页后的数据
     * 
     * @param pageNo
     *            需要获取的页码
     * @return 返回分页后的数据
     */
    public ArrayList<T> getPagedData(int pageNo) {
        // 如果data为空，则分页结果直接返回null，
        if (data == null || data.size() == 0) {
            return null;
        }
        // 检查页码是否越界
        if (pageNo < 0 || pageNo > allPage) {
            return null;
        }
        // 根据页码截取数据并返回
        ArrayList<T> ret = new ArrayList<T>();
        int index = 0;
        for (int i = 0; i < pagePerNum; i++) {
            index = (pageNo - 1) * pagePerNum + i;
            if (index < data.size()) {
                ret.add(data.get(index));
            } else {
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            al.add("AA" + (i + 1));
        }
        PageUtil<String> page = new PageUtil<String>(al);
        ArrayList<String> ret = page.getPagedData(3);
        for (int i = 0; i < ret.size(); i++) {
            System.out.println(ret.get(i));
        }
        System.out.println(page.getAllPage());
    }
}