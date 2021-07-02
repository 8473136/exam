# 考试系统数据库文档

**数据库名：** exam

**文档版本：** 1.0.0

**文档描述：** 数据库文档生成
| 表名                  | 说明       |
| :-------------------- | :--------- |
| [tab_exam_correct_paper](#tab_exam_correct_paper) |  |
| [tab_exam_dept](#tab_exam_dept) |  |
| [tab_exam_paper](#tab_exam_paper) |  |
| [tab_exam_paper_questions](#tab_exam_paper_questions) |  |
| [tab_exam_paper_user](#tab_exam_paper_user) |  |
| [tab_exam_questions](#tab_exam_questions) |  |
| [tab_exam_questions_number](#tab_exam_questions_number) |  |
| [tab_exam_questions_option](#tab_exam_questions_option) |  |
| [tab_exam_submit_paper](#tab_exam_submit_paper) |  |
| [tab_exam_user](#tab_exam_user) | 考试用户表 |
| [tab_sys_admin](#tab_sys_admin) |  |
| [tab_sys_dict](#tab_sys_dict) |  |
| [tab_sys_log](#tab_sys_log) |  |
| [tab_sys_menu](#tab_sys_menu) |  |
| [tab_sys_role](#tab_sys_role) |  |
| [tab_sys_role_menu](#tab_sys_role_menu) |  |
**表名：** <a id="tab_exam_correct_paper">tab_exam_correct_paper</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | paper_user_id |   int   | 10 |   0    |    Y     |  N   |       | 试卷用户关联表id  |
|  3   | submit_id |   int   | 10 |   0    |    Y     |  N   |       | 交卷id  |
|  4   | correct_paper_all_json |   varchar   | 5000 |   0    |    Y     |  N   |       | 全部改卷信息  |
|  5   | score |   decimal   | 10 |   2    |    Y     |  N   |       | 用户得分  |
|  6   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  7   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  8   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  9   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  10   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_dept">tab_exam_dept</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   intunsigned   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | dept_name |   varchar   | 255 |   0    |    Y     |  N   |       | 部门名称  |
|  3   | parent_id |   int   | 10 |   0    |    Y     |  N   |       | 上级部门  |
|  4   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  5   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  6   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  7   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  8   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_paper">tab_exam_paper</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | paper_name |   varchar   | 100 |   0    |    Y     |  N   |       | 试卷名称  |
|  3   | paper_content |   varchar   | 1024 |   0    |    Y     |  N   |       | 试卷说明  |
|  4   | paper_type |   varchar   | 64 |   0    |    Y     |  N   |       | 试卷类型字典表关联  |
|  5   | start_time |   datetime   | 19 |   0    |    Y     |  N   |       | 考试开始时间  |
|  6   | end_time |   datetime   | 19 |   0    |    Y     |  N   |       | 考试结束时间  |
|  7   | exam_duration |   int   | 10 |   0    |    Y     |  N   |       | 考试时长  |
|  8   | total_score |   decimal   | 10 |   2    |    Y     |  N   |       | 总分  |
|  9   | pass_score |   decimal   | 10 |   2    |    Y     |  N   |       | 合格分  |
|  10   | paper_status |   int   | 10 |   0    |    Y     |  N   |   0    | 试卷状态0-未发布1-已发布  |
|  11   | release_time |   datetime   | 19 |   0    |    Y     |  N   |       | 发布时间  |
|  12   | business_type |   varchar   | 64 |   0    |    Y     |  N   |       | 业务类型0默认类型1多选题错一个算错  |
|  13   | look_score_time |   datetime   | 19 |   0    |    Y     |  N   |       | 查看分数时间  |
|  14   | exam_count |   int   | 10 |   0    |    Y     |  N   |   1    | 可考次数  |
|  15   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  16   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  17   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  18   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  19   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_paper_questions">tab_exam_paper_questions</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | paper_id |   int   | 10 |   0    |    N     |  N   |       | 试卷id  |
|  3   | question_id |   int   | 10 |   0    |    N     |  N   |       | 题目id  |
**表名：** <a id="tab_exam_paper_user">tab_exam_paper_user</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | paper_id |   int   | 10 |   0    |    N     |  N   |       | 试卷id  |
|  3   | user_id |   int   | 10 |   0    |    N     |  N   |       | 用户id  |
|  4   | exam_status |   int   | 10 |   0    |    Y     |  N   |       | 考试状态0未考试1已考试  |
|  5   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  6   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  7   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  8   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  9   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_questions">tab_exam_questions</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | question_name |   varchar   | 1024 |   0    |    Y     |  N   |       | 题目名称  |
|  3   | question_type |   varchar   | 64 |   0    |    Y     |  N   |       | 题目类型字典表  |
|  4   | parent_question_id |   int   | 10 |   0    |    Y     |  N   |       | 父题案例分析-父题id  |
|  5   | question_source |   varchar   | 1024 |   0    |    Y     |  N   |       | 试题出处  |
|  6   | question_depot |   varchar   | 64 |   0    |    Y     |  N   |       | 题目所属库字典表  |
|  7   | question_analysis |   varchar   | 1024 |   0    |    Y     |  N   |       | 试题解析  |
|  8   | special_id |   varchar   | 64 |   0    |    Y     |  N   |       | 所属专题id  |
|  9   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  10   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  11   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  12   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  13   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_questions_number">tab_exam_questions_number</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | paper_id |   int   | 10 |   0    |    Y     |  N   |       | 试卷id  |
|  3   | question_type |   varchar   | 64 |   0    |    Y     |  N   |       | 题目类型读取字典表  |
|  4   | question_number |   int   | 10 |   0    |    Y     |  N   |       | 题目数量  |
|  5   | question_score |   decimal   | 10 |   2    |    Y     |  N   |       | 每题分数  |
|  6   | parent_id |   int   | 10 |   0    |    Y     |  N   |       | 父题id用于案例分析题子题  |
|  7   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  8   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  9   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  10   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  11   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_questions_option">tab_exam_questions_option</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | option_content |   varchar   | 1024 |   0    |    Y     |  N   |       | 选项内容  |
|  3   | question_id |   int   | 10 |   0    |    Y     |  N   |       | 题目id  |
|  4   | is_right_key |   int   | 10 |   0    |    Y     |  N   |       | 是否为正确答案0正确1错误  |
|  5   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  6   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  7   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  8   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  9   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_exam_submit_paper">tab_exam_submit_paper</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | paper_id |   int   | 10 |   0    |    N     |  N   |       | 试卷id  |
|  3   | user_id |   int   | 10 |   0    |    N     |  N   |       | 用户id  |
|  4   | commit_content |   varchar   | 6400 |   0    |    N     |  N   |       | 交卷内容(json)  |
|  5   | commit_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 交卷时间  |
|  6   | commit_type |   int   | 10 |   0    |    Y     |  N   |       | 交卷类型0提前交卷1自动交卷  |
|  7   | correct_paper_status |   int   | 10 |   0    |    Y     |  N   |   0    | 改卷状态0未改卷1改卷中2改卷完成3改卷错误  |
**表名：** <a id="tab_exam_user">tab_exam_user</a>

**说明：** 考试用户表

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键  |
|  2   | name |   varchar   | 64 |   0    |    Y     |  N   |       | 姓名  |
|  3   | dept_id |   int   | 10 |   0    |    Y     |  N   |       | 部门  |
|  4   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  5   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  6   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  7   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  8   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_admin">tab_sys_admin</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 用户id  |
|  2   | account |   varchar   | 64 |   0    |    N     |  N   |       | 用户名  |
|  3   | password |   varchar   | 64 |   0    |    N     |  N   |       | 密码  |
|  4   | salt |   varchar   | 64 |   0    |    N     |  N   |       | 盐  |
|  5   | nick_name |   varchar   | 64 |   0    |    Y     |  N   |       | 昵称  |
|  6   | dept_id |   int   | 10 |   0    |    Y     |  N   |       | 部门id  |
|  7   | role_id |   int   | 10 |   0    |    Y     |  N   |       | 权限id  |
|  8   | phone |   varchar   | 11 |   0    |    Y     |  N   |       | 电话  |
|  9   | user_status |   int   | 10 |   0    |    Y     |  N   |   0    | 用户状态0正常1未激活  |
|  10   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  11   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  12   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  13   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  14   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_dict">tab_sys_dict</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 自增id  |
|  2   | dict_name |   varchar   | 128 |   0    |    N     |  N   |       | 字典名称  |
|  3   | parent_id |   int   | 10 |   0    |    Y     |  N   |       | 父级id  |
|  4   | dict_code |   varchar   | 255 |   0    |    Y     |  N   |       | 字典编码  |
|  5   | dict_describe |   varchar   | 255 |   0    |    Y     |  N   |       | 字典描述  |
|  6   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  7   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  8   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  9   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  10   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_log">tab_sys_log</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | log_type |   varchar   | 64 |   0    |    Y     |  N   |       | 日志类型  |
|  3   | exec_method |   varchar   | 255 |   0    |    Y     |  N   |       | 方法名  |
|  4   | params |   varchar   | 9999 |   0    |    Y     |  N   |       | 参数  |
|  5   | exec_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 操作时间  |
|  6   | exec_ip |   varchar   | 255 |   0    |    Y     |  N   |       | 操作ip  |
|  7   | business |   varchar   | 255 |   0    |    Y     |  N   |       | 操作业务  |
|  8   | log_content |   varchar   | 10000 |   0    |    Y     |  N   |       | 具体内容  |
|  9   | module |   varchar   | 255 |   0    |    Y     |  N   |       | 操作模块  |
|  10   | user_id |   int   | 10 |   0    |    Y     |  N   |       | 用户id  |
|  11   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  12   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  13   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  14   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  15   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_menu">tab_sys_menu</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | title |   varchar   | 255 |   0    |    Y     |  N   |       | 菜单标题  |
|  3   | icon |   varchar   | 255 |   0    |    Y     |  N   |       | 菜单图标  |
|  4   | href |   varchar   | 255 |   0    |    Y     |  N   |       | 菜单地址  |
|  5   | parent_id |   int   | 10 |   0    |    Y     |  N   |       | 父菜单id  |
|  6   | menu_order |   int   | 10 |   0    |    Y     |  N   |       | 排序  |
|  7   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  8   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  9   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  10   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  11   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_role">tab_sys_role</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | id  |
|  2   | role_name |   varchar   | 255 |   0    |    Y     |  N   |       | 权限名称  |
|  3   | remark |   varchar   | 1000 |   0    |    Y     |  N   |       | 权限描述  |
|  4   | create_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 创建时间  |
|  5   | update_time |   datetime   | 19 |   0    |    Y     |  N   |   CURRENT_TIMESTAMP    | 修改时间  |
|  6   | create_by |   int   | 10 |   0    |    Y     |  N   |       | 创建人  |
|  7   | update_by |   int   | 10 |   0    |    Y     |  N   |       | 修改人  |
|  8   | is_deleted |   int   | 10 |   0    |    Y     |  N   |   0    | 删除标识0正常1删除  |
**表名：** <a id="tab_sys_role_menu">tab_sys_role_menu</a>

**说明：** 

**数据列：**

| 序号 | 名称 | 数据类型 |  长度  | 小数位 | 允许空值 | 主键 | 默认值 | 说明 |
| :--: | :--- | :------: | :----: | :----: | :------: | :--: | :----: | :--: |
|  1   | id |   int   | 10 |   0    |    N     |  Y   |       | 主键id  |
|  2   | role_id |   int   | 10 |   0    |    Y     |  N   |       | 权限id  |
|  3   | menu_id |   int   | 10 |   0    |    Y     |  N   |       | 菜单id  |
