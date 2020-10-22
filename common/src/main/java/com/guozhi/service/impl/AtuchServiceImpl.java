package com.guozhi.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.guozhi.common.DataGlobalVariable;
import com.guozhi.common.JwtPayload;
import com.guozhi.core.BusinessException;
import com.guozhi.dto.UserDTO;
import com.guozhi.exception.GlobalException;
import com.guozhi.mapper.RoleMenuMapper;
import com.guozhi.mapper.UserMapper;
import com.guozhi.rvo.InitialHomeRVO;
import com.guozhi.rvo.LoginRVO;
import com.guozhi.rvo.RoleMenuRVO;
import com.guozhi.service.AuthService;
import com.guozhi.utils.JwtUtils;
import com.guozhi.utils.MD5Utils;
import com.guozhi.utils.UUIDUtils;
import com.guozhi.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiuchangLan
 * @date 2020/7/16 14:34
 */
@Service
public class AtuchServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public LoginRVO login(LoginVO loginVO) throws BusinessException {
        UserDTO userDTO = new UserDTO();
        userDTO.setIsDeleted(0);
        userDTO.setAccount(loginVO.getAccount());
        UserDTO loginUserInfo = userMapper.selectOne(userDTO);

        // 用户不存在
        if (loginUserInfo == null) {
            throw new BusinessException(GlobalException.ACCOUNT_NOT_EXISTS);
        }

        // 判断密码
        if (!MD5Utils.encryption(loginVO.getPassword(), loginUserInfo.getSalt()).equals(loginUserInfo.getPassword())) {
            throw new BusinessException(GlobalException.ACCOUNT_OR_PASSWORD_INCORRECT);
        }

        // 判断用户状态
        if (DataGlobalVariable.USER_STATUS_DISABLE.equals(loginUserInfo.getUserStatus())) {
            throw new BusinessException(GlobalException.ACCOUNT_DISABLE);
        }

        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setAccount(loginUserInfo.getAccount());
        jwtPayload.setId(loginUserInfo.getId());
        jwtPayload.setDeptId(loginUserInfo.getDeptId());
        jwtPayload.setNickName(loginUserInfo.getNickName());

        String accessToken = JwtUtils.createToken(jwtPayload, 2 * 60 * 1000L);
        String refreshToken = UUIDUtils.ramdomUUID();

        return new LoginRVO(accessToken,refreshToken,System.currentTimeMillis());
    }

    @Override
    public String refreshToken(String accessToken) {
        JwtPayload jwtPayload = JwtUtils.decrypt(accessToken);
        return JwtUtils.createToken(jwtPayload, 2 * 60 * 1000L);
    }

    @Override
    public InitialHomeRVO getLoginMenus() {
        InitialHomeRVO initialHomeRVO = new InitialHomeRVO();
        // 设置首页信息
        initialHomeRVO.getHomeInfo().put("title","首页");
        initialHomeRVO.getHomeInfo().put("href","page/home/welcome.html?t=1");
        // logo信息
        initialHomeRVO.getLogoInfo().put("title","考试系统");
        initialHomeRVO.getLogoInfo().put("image","images/logo.png");
        initialHomeRVO.getLogoInfo().put("href","");
        // 登录的用户id
        Integer userId = JwtUtils.getCurrentUserJwtPayload().getId();
        Integer roleId = userMapper.selectByPrimaryKey(userId).getRoleId();
        List<RoleMenuRVO> roleMenu = roleMenuMapper.getRoleMenu(roleId);

        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setIdKey("id");
        treeNodeConfig.setNameKey("title");
        treeNodeConfig.setChildrenKey("child");
        // 最大递归深度
        treeNodeConfig.setDeep(100);

        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(roleMenu, "-1", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId().toString());
                    tree.setParentId(treeNode.getParentId().toString());
                    tree.setName(treeNode.getTitle());
                    tree.putExtra("href",treeNode.getHref());
                    tree.putExtra("icon",treeNode.getIcon());
                });
        initialHomeRVO.setMenuInfo(treeNodes);
//        List<RoleMenuRVO> list = TreeUtils.buildTreeHome(roleMenu, -1);
//        return list;
        return initialHomeRVO;
    }
}
