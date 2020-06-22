import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hs.dao.ConsumerDao;
import com.hs.dao.DeptDao;
import com.hs.dao.GoodsDao;
import com.hs.entity.Consumer;
import com.hs.entity.Dept;
import com.hs.entity.Goods;
import com.util.PayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @description:
 * @author: 彭于晏
 * @create: 2020-03-11 17:23
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test1 {
    @Autowired
    private ConsumerDao consumerDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private DeptDao deptDao;

    @Test
    public void test1(){
        PageHelper.startPage(1,4);
        List<Dept> all = deptDao.getAll();
        PageInfo<Dept> pageInfo=new PageInfo<Dept>(all);
        for (Dept dept : pageInfo.getList()) {
            System.out.println(dept);
        }
        System.out.println("getList"+pageInfo.getList());
        System.out.println("total"+pageInfo.getTotal());
        System.out.println("page"+pageInfo.getPageNum());
        System.out.println("endrow"+pageInfo.getEndRow());
        System.out.println("pages"+pageInfo.getPages());
        System.out.println(pageInfo.getPrePage());

    }
    @Test
    public void test2(){
        List<Goods> goods = goodsDao.queryGoods("1232");
        System.out.println(goods);
    }

    @Test
    public void zhifu(){
//        String str = PayUtils.alipay("23131312", "0.01", "hehe", "haha");
//        System.out.println(str);
        long time = System.currentTimeMillis();
        String res = String.valueOf(time/1000);
        System.out.println(time+"\t"+res);

    }
}
