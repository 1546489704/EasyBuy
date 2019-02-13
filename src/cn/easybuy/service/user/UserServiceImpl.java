package cn.easybuy.service.user;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.mapper.UserMapper;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.MyBatisUtil;
import cn.easybuy.utils.Pager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import cn.easybuy.entity.User;

public class UserServiceImpl implements UserService {
	
	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	/**
	 * 增加用户 使用mybatis改造
	 * @param user
	 * @return
	 */
	@Override
	public boolean add(User user){
		SqlSession sqlSession=null;
		Integer count=0;
		try {
			sqlSession = MyBatisUtil.createSqlSession();
			count=sqlSession.getMapper(UserMapper.class).add(user);
			logger.debug(count);
			//提交事务
			sqlSession.commit();
		} catch (SQLException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			sqlSession.close();
			return  count>0;
		}
	}

	/**
	 * 修改用户使用mybatis改造
	 * @param user
	 * @return
	 */
	@Override
	public boolean update(User user) {
		Integer count=0;
		SqlSession sqlSession=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			count=sqlSession.getMapper(UserMapper.class).update(user);
			logger.debug(count);
			if (count>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (SQLException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
			return  count>0;
		}
	}

	/**
	 * 删除用户 使用mybatis改造
	 * @param userId
	 * @return
	 */
	@Override
	public boolean deleteUserById(Integer userId) {
		SqlSession sqlSession=null;
		Integer count=0;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			count=sqlSession.getMapper(UserMapper.class).deleteUserById(userId+"");
			if(count>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (SQLException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
			return  count>0;
		}
	}

	/**
	 * 登入  使用mybatis改造过了的
	 * @param userId
	 * @param loginName
	 * @return
	 */
	@Override
	public User getUser(Integer userId, String loginName) {
		SqlSession sqlSession=null;
		User user=null;
		try {

			sqlSession = MyBatisUtil.createSqlSession();
			user=sqlSession.getMapper(UserMapper.class).getUser(userId,loginName);
			sqlSession.commit(false);
		} catch (SQLException e) {
			sqlSession.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}finally {
			if(sqlSession!=null){
				sqlSession.close();
			}
			return user;
		}
	}

	/**
	 * 查询所有用户
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
		List<User> userList=null;
		SqlSession sqlSession=null;
		try {
			int total =count();
			Pager pager = new Pager(total, pageSize, currentPageNo);
			sqlSession=MyBatisUtil.createSqlSession();
			userList=sqlSession.getMapper(UserMapper.class).getUserList((pager.getCurrentPage() - 1) * pager.getRowPerPage() , pager.getRowPerPage());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
			return userList;
		}
	}

	@Override
	public int count() {
	SqlSession sqlSession=null;
		Integer count=null;
		try {
			sqlSession=MyBatisUtil.createSqlSession();
			count=sqlSession.getMapper(UserMapper.class).count();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			MyBatisUtil.closeSqlSession(sqlSession);
			return count;
		}
	}
}
