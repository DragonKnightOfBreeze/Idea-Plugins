# 版本

## 1.x

### 1.0

* 创建语言文件类型和图标
* 更新中
* 更新和整理代码
* 完成基础功能

### 1.1

* 更新版本

### 1.2

* [X] 脚本文件：允许变量名中有大写字母
* [X] 脚本文件：代码格式设置和格式化
* [X] 脚本文件：代码检查 - ［警告］重复的变量定义 → 导航到重复的变量定义
* [X] 脚本文件：代码检查 - ［警告］非法的文件编码 → 转化为正确的文件编码（除了name_list是`UTF-8 BOM`之外，必须是`UTF-8 NO BOM`）
* [X] 本地化文件：代码格式设置和格式化
* [X] 本地化文件：代码检查 - ［警告］重复的属性 → 导航到重复的属性
* [X] 本地化文件：语法词法解析 - 允许属性名和冒号之间有空格，但是格式化时总是会去掉
* [X] 本地化文件：代码检查 - ［错误］不支持的语言区域 → 选择语言区域（语法上允许其他语言区域）
* [X] 本地化文件：代码检查 - ［警告］非法的文件编码 → 转化为正确的文件编码（必须是`UTF-8 BOM`）
* [X] 本地化文件：代码检查 - ［警告］非法的文件名 → ~~重命名文件名~~（必须包含语言区域字符串如`l_simp_chinese`）

### 1.3

* 更新项目文档

### 1.4

* [X] 更新项目代码结构
* [X] 完成引用部分代码，未完整测试
* [X] 本地化文件属性，脚本文件变量：实现定位定义功能
* [X] 本地化文件属性，脚本文件变量：实现提供文档注释功能
* [X] 完善文档注释功能。
* [X] 定位定义：脚本文件的字符串 - 可能的脚本文件/本地化文件的属性
* [X] 实现ElementDescriptionProvider，提供元素的描述（用于查找引用）
* [X] 完善文档注释功能和代码补全功能
* [X] 测试通过查找使用功能
* [X] 完善对本地化文件富文本的支持
* [X] 删除多余的代码
* [X] 本地化文件的属性引用兼容额外参数
* [X] 对于属性引用的代码提示（来自属性的引用）
* [X] 解决BUG：脚本文件Lexer的问题导致Editor永久卡顿下去的恶性BUG（当输入propertyKey时，Lexer468行，不要随便使用`yypushback()`）
* [X] 解决BUG：本地化文件属性的查找使用只能找到单个使用的静态代码提示不起作用（重载为`PsiPolyVariantReference.resolveReference()`）
* [X] 解决BUG：时间区域的静态代码提示不起作用（pattern不对，需要通过代码调试得出）
* [X] 解决BUG：脚本文件关键词的静态代码提示不起作用（pattern不对，需要通过代码调试得出）
* [X] 对于语言区域的代码提示（确保匹配范围准确）

### 1.5

* [X] 解决关于代码补全的小bug。

### 1.6
 
修复一些BUG和问题。
 
* [X] LocalizationPropertyKey/ScriptPropertyKey可以包含`-`
* [X] ScriptPropertyKey/ScriptText实际上都可以仅包含大写/小写字母
* [X] ScriptPropertyKey可以是`NOT` `NOR` `AND` `OR`
* [X] LocalizationProperty可以不包含number（在`localization_synced`目录中）
* [X] 将GutterIcon的尺寸调小一点。
* [X] 支持gfx引用文件（`.gfx`）
* [X] ScriptUnquotedString只要不包含空格即可
* [X] 仅对顶层的ScriptProperty添加文档注释并视为可能的引用
* [X] 对于变量的代码提示（来自变量的引用）
* [X] 更新项目描述
* [X] 高亮转义字符
* [X] 重构和重命名Psi
* [X] 修复BUG

### 1.7

语法兼容性优化。

* [X] 修复对于语言区域的代码补全中的BUG，当提示时包含已输入的字符
* [X] 为脚本文件提供Json Schema的支持（完成代码的编写，但未测试）
* [X] annotator解析多个引用结果
* [X] 更新项目文档
* [X] 优化性能
* [X] 取消在页面右边显示彩色文本的颜色（因为可能有很多）
* [X] "="周围可以没有空格（限制unquotedString）
* [X] 限制scriptUnquotedString：不包含`{}[]="\s`
* [X] scriptUnquotedString实际上也可以不包含大写字母（允许的属性名：`portraits`，来自name_list）
* [X] scriptPropertyName，不是顶级属性时，仍然可以包含大写字母（但不以大写字母开头）
* [X] 图标名也可以是属性引用
* [X] 认为序号必须是`%x%`，其中`x`长度只能为1，同时`%`不需要在字符串中转义
* [X] 颜色文本可以嵌套
* [X] LocalIntention不要直接定义为object，可能存在问题
* [X] scriptString显示文本时去除包围的引号
* [X] ClassCastException（数组不要直接转换泛型）

### 1.8

语法兼容性优化。

* [X] 数字可以是负数
* [X] 值（字符串）也可以在顶层
* [X] 数字也可以作为数组的元素，即，`{1 2 3}`是合法的
* [X] LocalIntention：ClassNotFoundException（`intentionAction.className`标签中不能有任何空格）（黑人问号？？？）
* [X] 更新结构视图和面包屑导航以匹配完善后的语法。

# TODO

* [ ] 兼容颜色语法（作为一种类型）：`rgb { 142 188 241 }` 
* [ ] 本地化属性的名字重复检查有bug，当有多个属性引用时，总是报错（？）
* [ ] 本地化文件中属性默认缩进1空格对齐（？）

* [ ] 更多的引用类型：
  * [ ] event_id
  * [ ] gfx_reference
  * [ ] path
* [ ] 为脚本文件编写基础的Json Schema
* [ ] 根据脚本文件的位置和后缀名，为其应用对应的Json Schema
* [ ] DocComment：仅允许第一个空行之前的LineComment作为DocComment

