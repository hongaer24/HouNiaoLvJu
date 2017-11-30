package cn.houno.houniaolvju.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 城市列表所有数据
 * Created by Administrator on 2017/2/9.
 */

public class FlightCityListBean {


    /**
     * status : 0
     * data : {"hot":[{"id":"1","areaname":"上海","sajm":"SHA","country":"中国","cjm":"CN","sijm":"ZSSS","airport":"上海虹桥机场","enname":"SHANGHAI","areaen":"shanghai","simpleen":"sh","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"1"},{"id":"2","areaname":"北京","sajm":"PEK","country":"中国","cjm":"CN","sijm":"ZBAA","airport":"北京首都机场","enname":"BEIJING","areaen":"beijing","simpleen":"bj","jianpin":"B","ishot":"1","status":"1","remark":"","row_number":"2"},{"id":"3","areaname":"广州","sajm":"CAN","country":"中国","cjm":"CN","sijm":"ZGGG","airport":"广州白云机场","enname":"GUANGZHOU","areaen":"guangzhou","simpleen":"gz","jianpin":"G","ishot":"1","status":"1","remark":"","row_number":"3"},{"id":"4","areaname":"深圳","sajm":"SZX","country":"中国","cjm":"CN","sijm":"ZGSZ","airport":"深圳宝安机场","enname":"SHENZHEN","areaen":"shenzhen","simpleen":"sz","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"4"},{"id":"7","areaname":"天津","sajm":"TSN","country":"中国","cjm":"CN","sijm":"ZBTJ","airport":"天津滨海机场","enname":"TIANJIN","areaen":"tianjin","simpleen":"tj","jianpin":"T","ishot":"1","status":"1","remark":"","row_number":"5"},{"id":"9","areaname":"杭州","sajm":"HGH","country":"中国","cjm":"CN","sijm":"ZSHC","airport":"杭州萧山机场","enname":"HANGZHOU","areaen":"hangzhou","simpleen":"hj","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"6"},{"id":"10","areaname":"重庆","sajm":"CKG","country":"中国","cjm":"CN","sijm":"ZUCK","airport":"重庆江北机场","enname":"CHONGQING","areaen":"chongqing","simpleen":"cq","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"7"},{"id":"12","areaname":"成都","sajm":"CTU","country":"中国","cjm":"CN","sijm":"ZUUU","airport":"成都双流机场","enname":"CHENGDU","areaen":"chengdu","simpleen":"cd","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"8"},{"id":"13","areaname":"青岛","sajm":"TAO","country":"中国","cjm":"CN","sijm":"ZSQD","airport":"青岛流亭机场","enname":"QINGDAO","areaen":"qingdao","simpleen":"qd","jianpin":"Q","ishot":"1","status":"1","remark":"","row_number":"9"},{"id":"14","areaname":"西安","sajm":"XIY","country":"中国","cjm":"CN","sijm":"ZLXY","airport":"西安咸阳机场","enname":"XIAN","areaen":"xian","simpleen":"xa","jianpin":"X","ishot":"1","status":"1","remark":"","row_number":"10"},{"id":"19","areaname":"大连","sajm":"DLC","country":"中国","cjm":"CN","sijm":"ZYTL","airport":"大连国际机场","enname":"DALIAN","areaen":"dalian","simpleen":"dl","jianpin":"D","ishot":"1","status":"1","remark":"","row_number":"11"},{"id":"25","areaname":"福州","sajm":"FOC","country":"中国","cjm":"CN","sijm":"ZSFZ","airport":"福州长乐机场","enname":"FUZHOU","areaen":"fuzhou","simpleen":"fz","jianpin":"F","ishot":"1","status":"1","remark":"","row_number":"12"},{"id":"32","areaname":"长沙","sajm":"CSX","country":"中国","cjm":"CN","sijm":"ZGHA","airport":"长沙黄花机场","enname":"CHANGSHA","areaen":"changsha","simpleen":"cs","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"13"},{"id":"33","areaname":"三亚","sajm":"SYX","country":"中国","cjm":"CN","sijm":"ZJSY","airport":"三亚凤凰机场","enname":"SANYA","areaen":"sanya","simpleen":"sy","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"14"},{"id":"53","areaname":"海口","sajm":"HAK","country":"中国","cjm":"CN","sijm":"ZJHK","airport":"海口美兰机场","enname":"HAIKOU","areaen":"haikou","simpleen":"hk","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"15"},{"id":"55","areaname":"郑州","sajm":"CGO","country":"中国","cjm":"CN","sijm":"ZHCC","airport":"郑州新郑机场","enname":"ZHENGZHOU","areaen":"zhengzhou","simpleen":"zz","jianpin":"Z","ishot":"1","status":"1","remark":"","row_number":"16"}],"other":{"A":[{"id":"43","areaname":"阿克苏","sajm":"AKU","country":"中国","cjm":"CN","sijm":"ZWAK","airport":"阿克苏机场","enname":"AKSU","areaen":"aksu","simpleen":"aks","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"1"},{"id":"38","areaname":"阿勒泰","sajm":"AAT","country":"中国","cjm":"CN","sijm":"ZWAT","airport":"阿勒泰机场","enname":"ALTAY","areaen":"aletai","simpleen":"alt","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"2"},{"id":"45","areaname":"安康","sajm":"AKA","country":"中国","cjm":"CN","sijm":"ZLAK","airport":"安康机场","enname":"ANKANG","areaen":"ankang","simpleen":"ak","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"3"},{"id":"69","areaname":"安庆","sajm":"AQG","country":"中国","cjm":"CN","sijm":"ZSAQ","airport":"安庆(天柱山）机场","enname":"ANQING","areaen":"anqing","simpleen":"aq","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"4"},{"id":"109","areaname":"鞍山","sajm":"AOG","country":"中国","cjm":"CN","sijm":"ZYAS","airport":"鞍山机场","enname":"ANSHAN","areaen":"anshan","simpleen":"as","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"5"},{"id":"91","areaname":"安阳","sajm":"AYN","country":"中国","cjm":"CN","sijm":"ZHAY","airport":"安阳北郊机场","enname":"ANYANG","areaen":"anyang","simpleen":"ay","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"6"}],"B":[{"id":"98","areaname":"保山","sajm":"BSD","country":"中国","cjm":"CN","sijm":"ZPBS","airport":"保山云瑞机场","enname":"BAOSHAN","areaen":"baoshan","simpleen":"bs","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"7"},{"id":"96","areaname":"包头","sajm":"BAV","country":"中国","cjm":"CN","sijm":"ZBOW","airport":"包头二里半机场","enname":"BAOTOU","areaen":"baotou","simpleen":"bt","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"8"},{"id":"40","areaname":"北海","sajm":"BHY","country":"中国","cjm":"CN","sijm":"ZGBH","airport":"北海福城机场","enname":"BEIHAI","areaen":"beihai","simpleen":"bh","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"9"},{"id":"2","areaname":"北京","sajm":"PEK","country":"中国","cjm":"CN","sijm":"ZBAA","airport":"北京首都机场","enname":"BEIJING","areaen":"beijing","simpleen":"bj","jianpin":"B","ishot":"1","status":"1","remark":"","row_number":"10"},{"id":"90","areaname":"蚌埠","sajm":"BFU","country":"中国","cjm":"CN","sijm":"ZSBB","airport":"蚌埠机场","enname":"BENGBU","areaen":"bengbu","simpleen":"bb","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"11"},{"id":"166","areaname":"博鳌","sajm":"BAR","country":"中国","cjm":"CN","sijm":"ZJQH","airport":"博鳌国际机场","enname":"BOAO","areaen":"boao","simpleen":"ba","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"12"}],"C":[{"id":"26","areaname":"长春","sajm":"CGQ","country":"中国","cjm":"CN","sijm":"ZYCC","airport":"长春龙嘉国际机场","enname":"CHANGCHUN","areaen":"changchun","simpleen":"cc","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"13"},{"id":"93","areaname":"常德","sajm":"CGD","country":"中国","cjm":"CN","sijm":"ZGCD","airport":"常德桃花源(斗姆湖)机场","enname":"CHANGDE","areaen":"changde","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"14"},{"id":"92","areaname":"昌都","sajm":"BPX","country":"中国","cjm":"CN","sijm":"ZUBD","airport":"西藏昌都邦达机场","enname":"CHAMDO","areaen":"changdu","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"15"},{"id":"62","areaname":"昌都","sajm":"BPX","country":"中国","cjm":"CN","sijm":"ZUBD","airport":"昌都邦达机场","enname":"BARDUFOSS","areaen":"changdu","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"16"},{"id":"106","areaname":"长海","sajm":"CNI","country":"中国","cjm":"CN","sijm":"ZYCH","airport":"长海（大长山岛）机场","enname":"DALIAN","areaen":"changhai","simpleen":"ch","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"17"},{"id":"32","areaname":"长沙","sajm":"CSX","country":"中国","cjm":"CN","sijm":"ZGHA","airport":"长沙黄花机场","enname":"CHANGSHA","areaen":"changsha","simpleen":"cs","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"18"},{"id":"95","areaname":"长治","sajm":"CIH","country":"中国","cjm":"CN","sijm":"ZBCZ","airport":"长治王村机场","enname":"CHANGZHI","areaen":"changzhi","simpleen":"cz","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"19"},{"id":"46","areaname":"常州","sajm":"CZX","country":"中国","cjm":"CN","sijm":"ZSCG","airport":"常州奔牛机场","enname":"CHANGZHOU","areaen":"changzhou","simpleen":"cz","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"20"},{"id":"99","areaname":"朝阳","sajm":"CHG","country":"中国","cjm":"CN","sijm":"ZYCY","airport":"辽宁朝阳机场","enname":"CHAOYANG","areaen":"chaoyang","simpleen":"cy","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"21"},{"id":"12","areaname":"成都","sajm":"CTU","country":"中国","cjm":"CN","sijm":"ZUUU","airport":"成都双流机场","enname":"CHENGDU","areaen":"chengdu","simpleen":"cd","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"22"},{"id":"101","areaname":"赤峰","sajm":"CIF","country":"中国","cjm":"CN","sijm":"ZBCF","airport":"赤峰玉龙机场","enname":"CHIFENG","areaen":"chifeng","simpleen":"cf","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"23"},{"id":"10","areaname":"重庆","sajm":"CKG","country":"中国","cjm":"CN","sijm":"ZUCK","airport":"重庆江北机场","enname":"CHONGQING","areaen":"chongqing","simpleen":"cq","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"24"}],"D":[{"id":"48","areaname":"大理","sajm":"DLU","country":"中国","cjm":"CN","sijm":"ZPDL","airport":"大理荒草坝机场","enname":"DALIXIAGUAN","areaen":"dali","simpleen":"dl","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"25"},{"id":"19","areaname":"大连","sajm":"DLC","country":"中国","cjm":"CN","sijm":"ZYTL","airport":"大连国际机场","enname":"DALIAN","areaen":"dalian","simpleen":"dl","jianpin":"D","ishot":"1","status":"1","remark":"","row_number":"26"},{"id":"107","areaname":"丹东","sajm":"DDG","country":"中国","cjm":"CN","sijm":"ZYDD","airport":"丹东浪头机场","enname":"DANDONG","areaen":"dandong","simpleen":"dd","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"27"},{"id":"105","areaname":"大庆","sajm":"DQA","country":"中国","cjm":"CN","sijm":"ZYDQ","airport":"大庆萨尔图机场","enname":"DAQING","areaen":"daqing","simpleen":"dq","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"28"},{"id":"102","areaname":"大同","sajm":"DAT","country":"中国","cjm":"CN","sijm":"ZBDT","airport":"山西大同机场","enname":"DATONG","areaen":"datong","simpleen":"dt","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"29"},{"id":"103","areaname":"达州","sajm":"DAX","country":"中国","cjm":"CN","sijm":"ZUDX","airport":"达州(河市)机场","enname":"DAZHOU","areaen":"dazhou","simpleen":"dz","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"30"},{"id":"104","areaname":"迪庆","sajm":"DIG","country":"中国","cjm":"CN","sijm":"ZPDQ","airport":"迪庆香格里拉机场","enname":"DIQING","areaen":"diqing","simpleen":"dq","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"31"},{"id":"77","areaname":"敦煌","sajm":"DNH","country":"中国","cjm":"CN","sijm":"ZLDH","airport":"敦煌机场","enname":"DUNHUANG","areaen":"dunhuang","simpleen":"dh","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"32"}],"E":[{"id":"37","areaname":"鄂尔多斯","sajm":"DSN","country":"中国","cjm":"CN","sijm":"ZBDS","airport":"鄂尔多斯伊金霍洛机场","enname":"ORDOS","areaen":"eerduosi","simpleen":"eeds","jianpin":"E","ishot":"","status":"1","remark":"","row_number":"33"},{"id":"112","areaname":"恩施","sajm":"ENH","country":"中国","cjm":"CN","sijm":"ZHES","airport":"恩施机场","enname":"ENSHI","areaen":"enshi","simpleen":"es","jianpin":"E","ishot":"","status":"1","remark":"","row_number":"34"}],"F":[{"id":"113","areaname":"阜阳","sajm":"FUG","country":"中国","cjm":"CN","sijm":"ZSFY","airport":"阜阳西关机场","enname":"FUYANG","areaen":"fuyang","simpleen":"fy","jianpin":"F","ishot":"","status":"1","remark":"","row_number":"35"},{"id":"100","areaname":"富蕴","sajm":"FYN","country":"中国","cjm":"CN","sijm":"ZWFY","airport":"富蕴机场","enname":"FUYUN","areaen":"fuyun","simpleen":"fy","jianpin":"F","ishot":"","status":"1","remark":"","row_number":"36"},{"id":"25","areaname":"福州","sajm":"FOC","country":"中国","cjm":"CN","sijm":"ZSFZ","airport":"福州长乐机场","enname":"FUZHOU","areaen":"fuzhou","simpleen":"fz","jianpin":"F","ishot":"1","status":"1","remark":"","row_number":"37"}],"G":[{"id":"67","areaname":"赣州","sajm":"KOW","country":"中国","cjm":"CN","sijm":"ZSGZ","airport":"赣州黄金机场","enname":"GANZHOU","areaen":"ganzhou","simpleen":"gz","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"38"},{"id":"8","areaname":"高雄","sajm":"KHH","country":"中国","cjm":"CN","sijm":"RCKH","airport":"高雄机场","enname":"GAOXIONG","areaen":"gaoxiong","simpleen":"gx","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"39"},{"id":"68","areaname":"格尔木","sajm":"GOQ","country":"中国","cjm":"CN","sijm":"ZLGM","airport":"格尔木机场","enname":"GOLMUD","areaen":"geermu","simpleen":"gem","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"40"},{"id":"70","areaname":"广汉","sajm":"GHN","country":"中国","cjm":"CN","sijm":"ZUGH","airport":"广汉机场","enname":"GUANGHAN","areaen":"guanghan","simpleen":"gh","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"41"},{"id":"3","areaname":"广州","sajm":"CAN","country":"中国","cjm":"CN","sijm":"ZGGG","airport":"广州白云机场","enname":"GUANGZHOU","areaen":"guangzhou","simpleen":"gz","jianpin":"G","ishot":"1","status":"1","remark":"","row_number":"42"},{"id":"72","areaname":"桂林","sajm":"KWL","country":"中国","cjm":"CN","sijm":"ZGKL","airport":"桂林两江机场","enname":"GUILIN","areaen":"guilin","simpleen":"gl","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"43"},{"id":"28","areaname":"贵阳","sajm":"KWE","country":"中国","cjm":"CN","sijm":"ZUGY","airport":"贵阳龙洞堡机场","enname":"GUIYANG","areaen":"guiyang","simpleen":"gy","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"44"}],"H":[{"id":"18","areaname":"哈尔滨","sajm":"HRB","country":"中国","cjm":"CN","sijm":"ZYHB","airport":"哈尔滨太平机场","enname":"HARBIN","areaen":"haerbin","simpleen":"hrb","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"45"},{"id":"53","areaname":"海口","sajm":"HAK","country":"中国","cjm":"CN","sijm":"ZJHK","airport":"海口美兰机场","enname":"HAIKOU","areaen":"haikou","simpleen":"hk","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"46"},{"id":"88","areaname":"海拉尔","sajm":"HLD","country":"中国","cjm":"CN","sijm":"ZBLA","airport":"内蒙古海拉尔东山机场","enname":"HAILAR","areaen":"hailaer","simpleen":"hle","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"47"},{"id":"76","areaname":"哈密","sajm":"HMI","country":"中国","cjm":"CN","sijm":"ZWHM","airport":"哈密机场","enname":"HAMI","areaen":"hami","simpleen":"hm","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"48"},{"id":"9","areaname":"杭州","sajm":"HGH","country":"中国","cjm":"CN","sijm":"ZSHC","airport":"杭州萧山机场","enname":"HANGZHOU","areaen":"hangzhou","simpleen":"hj","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"49"},{"id":"79","areaname":"汉中","sajm":"HZG","country":"中国","cjm":"CN","sijm":"ZLHZ","airport":"汉中西关机场","enname":"HANZHONG","areaen":"hanzhong","simpleen":"hz","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"50"},{"id":"80","areaname":"合肥","sajm":"HFE","country":"中国","cjm":"CN","sijm":"ZSOF","airport":"合肥骆岗机场","enname":"HEFEI","areaen":"hefei","simpleen":"hf","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"51"},{"id":"81","areaname":"黑河","sajm":"HEK","country":"中国","cjm":"CN","sijm":"ZYHE","airport":"黑河机场","enname":"HEIHE","areaen":"heihe","simpleen":"hh","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"52"},{"id":"82","areaname":"衡阳","sajm":"HNY","country":"中国","cjm":"CN","sijm":"ZGHY","airport":"衡阳东江机场","enname":"HENGYANG","areaen":"hengyang","simpleen":"hy","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"53"},{"id":"83","areaname":"和田","sajm":"HTN","country":"中国","cjm":"CN","sijm":"ZWTN","airport":"和田机场","enname":"HETIAN","areaen":"hetian","simpleen":"ht","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"54"},{"id":"73","areaname":"怀化","sajm":"HJJ","country":"中国","cjm":"CN","sijm":"ZGCJ","airport":"怀化芷江机场","enname":"HUAIHUA","areaen":"huaihua","simpleen":"hh","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"55"},{"id":"66","areaname":"花莲","sajm":"HUN","country":"中国","cjm":"CN","sijm":"RCYU","airport":"花莲机场","enname":"HELENA","areaen":"hualian","simpleen":"hl","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"56"},{"id":"84","areaname":"黄山","sajm":"TXN","country":"中国","cjm":"CN","sijm":"ZSTX","airport":"黄山屯溪机场","enname":"HUANGSHAN","areaen":"huangshan","simpleen":"hs","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"57"},{"id":"23","areaname":"呼和浩特","sajm":"HET","country":"中国","cjm":"CN","sijm":"ZBHH","airport":"呼和浩特白塔国际机场","enname":"HOHHOT","areaen":"huhehaote","simpleen":"hhht","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"58"}],"J":[{"id":"87","areaname":"佳木斯","sajm":"JMU","country":"中国","cjm":"CN","sijm":"ZYJM","airport":"佳木斯机场","enname":"JIAMUSI","areaen":"jiamusi","simpleen":"jms","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"59"},{"id":"97","areaname":"吉安","sajm":"KNC","country":"中国","cjm":"CN","sijm":"ZSJA","airport":"吉安机场","enname":"JIAN","areaen":"jian","simpleen":"ja","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"60"},{"id":"86","areaname":"嘉义","sajm":"CYI","country":"中国","cjm":"CN","sijm":"RCKU","airport":"嘉义机场","enname":"JIAYI","areaen":"jiayi","simpleen":"jy","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"61"},{"id":"110","areaname":"嘉峪关","sajm":"JGN","country":"中国","cjm":"CN","sijm":"ZLJQ","airport":"嘉峪关机场","enname":"JIAYUGUAN","areaen":"jiayuguan","simpleen":"jyg","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"62"},{"id":"139","areaname":"揭阳","sajm":"SWA","country":"中国","cjm":"CN","sijm":"ZGOW","airport":"揭阳潮汕机场","enname":"JIEYANG","areaen":"jieyang","simpleen":"jy","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"63"},{"id":"153","areaname":"吉林","sajm":"JIL","country":"中国","cjm":"CN","sijm":"ZYJL","airport":"吉林二台子机场","enname":"JILIN","areaen":"jilin","simpleen":"jl","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"64"},{"id":"49","areaname":"济南","sajm":"TNA","country":"中国","cjm":"CN","sijm":"ZSJN","airport":"济南遥墙机场","enname":"JINAN","areaen":"jinan","simpleen":"jn","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"65"},{"id":"143","areaname":"景德镇","sajm":"JDZ","country":"中国","cjm":"CN","sijm":"ZSJD","airport":"景德镇罗家机场","enname":"JINGDEZHEN","areaen":"jingdezhen","simpleen":"jdz","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"66"},{"id":"115","areaname":"济宁","sajm":"JNG","country":"中国","cjm":"CN","sijm":"ZSJG","airport":"济宁机场","enname":"JINING","areaen":"jining","simpleen":"jn","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"67"},{"id":"145","areaname":"晋江","sajm":"JJN","country":"中国","cjm":"CN","sijm":"ZSQZ","airport":"泉州晋江机场","enname":"QUANZHOU","areaen":"jinjang","simpleen":"jj","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"68"},{"id":"30","areaname":"锦州","sajm":"JNZ","country":"中国","cjm":"CN","sijm":"ZYJZ","airport":"锦州小岭子机场","enname":"JINZHOU","areaen":"jinzhou","simpleen":"jz","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"69"},{"id":"148","areaname":"九江","sajm":"JIU","country":"中国","cjm":"CN","sijm":"ZSJJ","airport":"九江庐山机场","enname":"JIUJIANG","areaen":"jiujiang","simpleen":"jj","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"70"},{"id":"147","areaname":"酒泉","sajm":"CHW","country":"中国","cjm":"CN","sijm":"NNNN","airport":"酒泉机场","enname":"JIUQUAN","areaen":"jiuquan","simpleen":"jq","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"71"},{"id":"78","areaname":"九寨沟","sajm":"JZH","country":"中国","cjm":"CN","sijm":"ZUJZ","airport":"九寨沟黄龙(九黄）机场","enname":"JIUZHAIGOU","areaen":"jiuzhaigou","simpleen":"jzg","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"72"}],"K":[{"id":"150","areaname":"喀什","sajm":"KHG","country":"中国","cjm":"CN","sijm":"ZWSH","airport":"喀什机场","enname":"KASHGAR","areaen":"kashi","simpleen":"ks","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"73"},{"id":"149","areaname":"克拉玛依","sajm":"KRY","country":"中国","cjm":"CN","sijm":"ZWKM","airport":"克拉玛依机场","enname":"KARAMAY","areaen":"kelamayi","simpleen":"klmy","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"74"},{"id":"152","areaname":"库车","sajm":"KCA","country":"中国","cjm":"CN","sijm":"ZWKC","airport":"库车机场","enname":"KUQA","areaen":"kuche","simpleen":"kc","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"75"},{"id":"165","areaname":"库尔勒","sajm":"KRL","country":"中国","cjm":"CN","sijm":"ZWKL","airport":"库尔勒机场","enname":"KORLA","areaen":"kuerle","simpleen":"kel","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"76"},{"id":"15","areaname":"昆明","sajm":"KMG","country":"中国","cjm":"CN","sijm":"ZPPP","airport":"昆明巫家坝机场","enname":"KUNMING","areaen":"kunming","simpleen":"km","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"77"}],"L":[{"id":"22","areaname":"兰州","sajm":"LHW","country":"中国","cjm":"CN","sijm":"ZLLL","airport":"兰州机场","enname":"LANZHOU","areaen":"lanzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"78"},{"id":"41","areaname":"拉萨","sajm":"LXA","country":"中国","cjm":"CN","sijm":"ZULS","airport":"拉萨贡嘎机场","enname":"LHASA","areaen":"lasa","simpleen":"ls","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"79"},{"id":"156","areaname":"连云港","sajm":"LYG","country":"中国","cjm":"CN","sijm":"ZSLG","airport":"连云港白塔埠机场","enname":"LIANYUNGANG","areaen":"lianyungang","simpleen":"lyg","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"80"},{"id":"157","areaname":"丽江","sajm":"LJG","country":"中国","cjm":"CN","sijm":"ZPLJ","airport":"丽江三义机场","enname":"LIJIANG","areaen":"lijiang","simpleen":"lj","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"81"},{"id":"65","areaname":"丽江","sajm":"LJG","country":"中国","cjm":"CN","sijm":"ZPLJ","airport":"丽江三义机场","enname":"LIJIANG","areaen":"lijiang","simpleen":"lj","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"82"},{"id":"154","areaname":"临沧","sajm":"LNJ","country":"中国","cjm":"CN","sijm":"ZPLC","airport":"临沧机场","enname":"LINCANG","areaen":"lincang","simpleen":"lc","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"83"},{"id":"158","areaname":"临沂","sajm":"LYI","country":"中国","cjm":"CN","sijm":"ZSLY","airport":"临沂沭埠岭机场","enname":"LINYI","areaen":"linyi","simpleen":"ly","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"84"},{"id":"50","areaname":"柳州","sajm":"LZH","country":"中国","cjm":"CN","sijm":"ZGZH","airport":"柳州白莲机场","enname":"LIUZHOU","areaen":"liuzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"85"},{"id":"51","areaname":"洛阳","sajm":"LYA","country":"中国","cjm":"CN","sijm":"ZHLY","airport":"洛阳机场","enname":"LUOYANG","areaen":"luoyang","simpleen":"ly","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"86"},{"id":"160","areaname":"泸州","sajm":"LZO","country":"中国","cjm":"CN","sijm":"ZULZ","airport":"泸州蓝田机场","enname":"LUZHOU","areaen":"luzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"87"}],"M":[{"id":"161","areaname":"芒市","sajm":"LUM","country":"中国","cjm":"CN","sijm":"ZPMS","airport":"芒市机场","enname":"MANGSHI","areaen":"mangshi","simpleen":"ms","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"88"},{"id":"162","areaname":"梅县","sajm":"MXZ","country":"中国","cjm":"CN","sijm":"ZGMX","airport":"梅县机场","enname":"MEIXIAN","areaen":"meixian","simpleen":"mx","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"89"},{"id":"164","areaname":"绵阳","sajm":"MIG","country":"中国","cjm":"CN","sijm":"ZUMY","airport":"绵阳南郊机场","enname":"MIANYANG","areaen":"mianyang","simpleen":"my","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"90"},{"id":"163","areaname":"牡丹江","sajm":"MDG","country":"中国","cjm":"CN","sijm":"ZYMD","airport":"牡丹江海浪机场","enname":"MUDANJIANG","areaen":"mudanjiang","simpleen":"mdj","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"91"}],"N":[{"id":"74","areaname":"那拉提","sajm":"NLT","country":"中国","cjm":"CN","sijm":"ZWNL","airport":"那拉提机场","enname":"NALATI","areaen":"nalati","simpleen":"nlt","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"92"},{"id":"27","areaname":"南昌","sajm":"KHN","country":"中国","cjm":"CN","sijm":"ZSCN","airport":"南昌昌北机场","enname":"NANCHANG","areaen":"nanchang","simpleen":"nc","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"93"},{"id":"125","areaname":"南充","sajm":"NAO","country":"中国","cjm":"CN","sijm":"ZUNC","airport":"南充高坪机场","enname":"NANCHONG","areaen":"nanchong","simpleen":"nc","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"94"},{"id":"17","areaname":"南京","sajm":"NKG","country":"中国","cjm":"CN","sijm":"ZSNJ","airport":"南京禄口机场","enname":"NANJING","areaen":"nanjing","simpleen":"nj","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"95"},{"id":"52","areaname":"南宁","sajm":"NNG","country":"中国","cjm":"CN","sijm":"ZGNN","airport":"南宁吴圩机场","enname":"NANNING","areaen":"nanning","simpleen":"nn","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"96"},{"id":"142","areaname":"南通","sajm":"NTG","country":"中国","cjm":"CN","sijm":"ZSNT","airport":"南通兴东机场","enname":"NANTONG","areaen":"nantong","simpleen":"nt","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"97"},{"id":"151","areaname":"南阳","sajm":"NNY","country":"中国","cjm":"CN","sijm":"ZHNY","airport":"南阳姜营机场","enname":"NANYANG","areaen":"nanyang","simpleen":"ny","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"98"},{"id":"24","areaname":"南苑","sajm":"NAY","country":"中国","cjm":"CN","sijm":"ZBNY","airport":"北京南苑机场","enname":"NANYUAN","areaen":"nanyuan","simpleen":"ny","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"99"},{"id":"117","areaname":"宁波","sajm":"NGB","country":"中国","cjm":"CN","sijm":"ZSNB","airport":"宁波栎社机场","enname":"NINGBO","areaen":"ningbo","simpleen":"nb","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"100"}],"P":[{"id":"6","areaname":"浦东","sajm":"PVG","country":"中国","cjm":"CN","sijm":"ZSPD","airport":"上海浦东机场","enname":"PUDONG","areaen":"pudong","simpleen":"pd","jianpin":"P","ishot":"","status":"1","remark":"","row_number":"101"}],"Q":[{"id":"118","areaname":"且末","sajm":"IQM","country":"中国","cjm":"CN","sijm":"ZWCM","airport":"且末机场","enname":"QIEMO","areaen":"qiemo","simpleen":"qm","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"102"},{"id":"13","areaname":"青岛","sajm":"TAO","country":"中国","cjm":"CN","sijm":"ZSQD","airport":"青岛流亭机场","enname":"QINGDAO","areaen":"qingdao","simpleen":"qd","jianpin":"Q","ishot":"1","status":"1","remark":"","row_number":"103"},{"id":"120","areaname":"庆阳","sajm":"IQN","country":"中国","cjm":"CN","sijm":"ZLQY","airport":"庆阳机场","enname":"QINGYANG","areaen":"qingyang","simpleen":"qy","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"104"},{"id":"121","areaname":"秦皇岛","sajm":"SHP","country":"中国","cjm":"CN","sijm":"ZBSH","airport":"秦皇岛山海关机场","enname":"QINHUANGDAO","areaen":"qinhuangdao","simpleen":"qhd","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"105"},{"id":"35","areaname":"齐齐哈尔","sajm":"NDG","country":"中国","cjm":"CN","sijm":"ZYQQ","airport":"齐齐哈尔三家子机场","enname":"QIQIHAER","areaen":"qiqihaer","simpleen":"qqhe","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"106"},{"id":"123","areaname":"衢州","sajm":"JUZ","country":"中国","cjm":"CN","sijm":"ZSJU","airport":"衢州机场","enname":"QUZHOU","areaen":"quzhou","simpleen":"qz","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"107"}],"S":[{"id":"33","areaname":"三亚","sajm":"SYX","country":"中国","cjm":"CN","sijm":"ZJSY","airport":"三亚凤凰机场","enname":"SANYA","areaen":"sanya","simpleen":"sy","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"108"},{"id":"1","areaname":"上海","sajm":"SHA","country":"中国","cjm":"CN","sijm":"ZSSS","airport":"上海虹桥机场","enname":"SHANGHAI","areaen":"shanghai","simpleen":"sh","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"109"},{"id":"89","areaname":"韶关","sajm":"HSC","country":"中国","cjm":"CN","sijm":"","airport":"韶关机场","enname":"SHAOGUAN","areaen":"shaoguan","simpleen":"sg","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"110"},{"id":"126","areaname":"沙市","sajm":"SHS","country":"中国","cjm":"CN","sijm":"ZHSS","airport":"荆州沙市机场","enname":"SHASHI","areaen":"shashi","simpleen":"ss","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"111"},{"id":"20","areaname":"沈阳","sajm":"SHE","country":"中国","cjm":"CN","sijm":"ZYTX","airport":"沈阳桃仙机场","enname":"SHENYANG","areaen":"shenyang","simpleen":"sy","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"112"},{"id":"4","areaname":"深圳","sajm":"SZX","country":"中国","cjm":"CN","sijm":"ZGSZ","airport":"深圳宝安机场","enname":"SHENZHEN","areaen":"shenzhen","simpleen":"sz","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"113"},{"id":"128","areaname":"石家庄","sajm":"SJW","country":"中国","cjm":"CN","sijm":"ZBSJ","airport":"石家庄正定机场","enname":"SHIJIAZHUANG","areaen":"shijiazhuang","simpleen":"sjz","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"114"},{"id":"129","areaname":"思茅","sajm":"SYM","country":"中国","cjm":"CN","sijm":"ZPSM","airport":"普洱(思茅)机场","enname":"PUER","areaen":"simao","simpleen":"sm","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"115"},{"id":"47","areaname":"苏州","sajm":"SZV","country":"中国","cjm":"CN","sijm":"ZSSZ","airport":"苏州机场","enname":"SUZHOU","areaen":"suzhou","simpleen":"sz","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"116"}],"T":[{"id":"131","areaname":"塔城","sajm":"TCG","country":"中国","cjm":"CN","sijm":"ZWTC","airport":"塔城机场","enname":"TACHENG","areaen":"tacheng","simpleen":"tc","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"117"},{"id":"5","areaname":"台北","sajm":"TSA","country":"中国","cjm":"CN","sijm":"RCSS","airport":"台北松山机场","enname":"TAIBEI","areaen":"taibei","simpleen":"tb","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"118"},{"id":"141","areaname":"台东","sajm":"TTG","country":"中国","cjm":"CN","sijm":"RCFN","airport":"台东志航机场","enname":"TAIDONG","areaen":"taidong","simpleen":"td","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"119"},{"id":"108","areaname":"台南","sajm":"TNN","country":"中国","cjm":"CN","sijm":"RCNN","airport":"台南机场","enname":"TAINAN","areaen":"tainan","simpleen":"tn","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"120"},{"id":"34","areaname":"太原","sajm":"TYN","country":"中国","cjm":"CN","sijm":"ZBYN","airport":"太原武宿机场","enname":"TAIYUAN","areaen":"taiyuan","simpleen":"ty","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"121"},{"id":"64","areaname":"台中","sajm":"RMQ","country":"中国","cjm":"CN","sijm":"RCMQ","airport":"台中清泉岗机场","enname":"TAICHUNG","areaen":"taizhong","simpleen":"tz","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"122"},{"id":"85","areaname":"台州","sajm":"HYN","country":"中国","cjm":"CN","sijm":"ZSLQ","airport":"台州路桥（原黄岩路桥）机场","enname":"TAIZHOU","areaen":"taizhou","simpleen":"tz","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"123"},{"id":"7","areaname":"天津","sajm":"TSN","country":"中国","cjm":"CN","sijm":"ZBTJ","airport":"天津滨海机场","enname":"TIANJIN","areaen":"tianjin","simpleen":"tj","jianpin":"T","ishot":"1","status":"1","remark":"","row_number":"124"},{"id":"134","areaname":"通化","sajm":"TNH","country":"中国","cjm":"CN","sijm":"ZYTN","airport":"通化机场","enname":"TONGHUA","areaen":"tonghua","simpleen":"th","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"125"},{"id":"135","areaname":"通辽","sajm":"TGO","country":"中国","cjm":"CN","sijm":"ZBTL","airport":"通辽机场","enname":"TONGLIAO","areaen":"tongliao","simpleen":"tl","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"126"},{"id":"136","areaname":"铜仁","sajm":"TEN","country":"中国","cjm":"CN","sijm":"ZUTR","airport":"铜仁凤凰机场","enname":"TONGREN","areaen":"tongren","simpleen":"tr","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"127"}],"W":[{"id":"137","areaname":"万州","sajm":"WXN","country":"中国","cjm":"CN","sijm":"ZUWX","airport":"重庆万州五桥机场","enname":"CHONGQING","areaen":"wanzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"128"},{"id":"138","areaname":"潍坊","sajm":"WEF","country":"中国","cjm":"CN","sijm":"ZSWF","airport":"潍坊机场","enname":"WEIFANG","areaen":"weifang","simpleen":"wf","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"129"},{"id":"144","areaname":"威海","sajm":"WEH","country":"中国","cjm":"CN","sijm":"ZSWH","airport":"威海文登大水泊机场","enname":"WEIHAI","areaen":"weihai","simpleen":"wh","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"130"},{"id":"75","areaname":"文山","sajm":"WNH","country":"中国","cjm":"CN","sijm":"ZPWS","airport":"云南文山普者黑机场","enname":"WENSHAN","areaen":"wenshan","simpleen":"ws","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"131"},{"id":"29","areaname":"温州","sajm":"WNZ","country":"中国","cjm":"CN","sijm":"ZSWZ","airport":"温州永强机场","enname":"WENZHOU","areaen":"wenzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"132"},{"id":"16","areaname":"武汉","sajm":"WUH","country":"中国","cjm":"CN","sijm":"ZHHH","airport":"武汉天河机场","enname":"WUHAN","areaen":"wuhan","simpleen":"wh","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"133"},{"id":"133","areaname":"乌兰浩特","sajm":"HLH","country":"中国","cjm":"CN","sijm":"ZBUL","airport":"乌兰浩特机场","enname":"ULANHOT","areaen":"wulanhaote","simpleen":"wlht","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"134"},{"id":"11","areaname":"乌鲁木齐","sajm":"URC","country":"中国","cjm":"CN","sijm":"ZWWW","airport":"乌鲁木齐地窝堡机场","enname":"URUMQI","areaen":"wulumuqi","simpleen":"wlmq","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"135"},{"id":"56","areaname":"无锡","sajm":"WUX","country":"中国","cjm":"CN","sijm":"ZSWX","airport":"无锡硕放机场","enname":"WUXI","areaen":"wuxi","simpleen":"wx","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"136"},{"id":"132","areaname":"武夷山","sajm":"WUS","country":"中国","cjm":"CN","sijm":"ZSWY","airport":"武夷山机场","enname":"WUYISHAN","areaen":"wuyishan","simpleen":"wys","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"137"},{"id":"130","areaname":"梧州","sajm":"WUZ","country":"中国","cjm":"CN","sijm":"ZGWZ","airport":"广西梧州长洲岛机场","enname":"WUZHOU","areaen":"wuzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"138"}],"X":[{"id":"61","areaname":"厦门","sajm":"XMN","country":"中国","cjm":"CN","sijm":"ZSAM","airport":"厦门高崎机场","enname":"XIAMEN","areaen":"xiamen","simpleen":"xm","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"139"},{"id":"14","areaname":"西安","sajm":"XIY","country":"中国","cjm":"CN","sijm":"ZLXY","airport":"西安咸阳机场","enname":"XIAN","areaen":"xian","simpleen":"xa","jianpin":"X","ishot":"1","status":"1","remark":"","row_number":"140"},{"id":"36","areaname":"襄阳","sajm":"XFN","country":"中国","cjm":"CN","sijm":"ZHXF","airport":"襄阳刘集机场","enname":"XIANGYANG","areaen":"xiangyang","simpleen":"xy","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"141"},{"id":"114","areaname":"西昌","sajm":"XIC","country":"中国","cjm":"CN","sijm":"ZUXC","airport":"西昌青山机场","enname":"XICHANG","areaen":"xichang","simpleen":"xc","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"142"},{"id":"60","areaname":"锡林浩特","sajm":"XIL","country":"中国","cjm":"CN","sijm":"ZBXH","airport":"锡林浩特机场","enname":"XILINHOT","areaen":"xilinhaote","simpleen":"xlht","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"143"},{"id":"94","areaname":"兴城","sajm":"XEN","country":"中国","cjm":"CN","sijm":"ZYXC","airport":"兴城机场","enname":"HULUDAO","areaen":"xingcheng","simpleen":"xc","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"144"},{"id":"124","areaname":"西宁","sajm":"XNN","country":"中国","cjm":"CN","sijm":"ZLXN","airport":"西宁曹家堡机场","enname":"XINING","areaen":"xining","simpleen":"xn","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"145"},{"id":"21","areaname":"西双版纳","sajm":"JHG","country":"中国","cjm":"CN","sijm":"ZPJH","airport":"景洪(西双版纳)机场","enname":"JINGHONG","areaen":"xishuangbanna","simpleen":"xsbn","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"146"},{"id":"59","areaname":"徐州","sajm":"XUZ","country":"中国","cjm":"CN","sijm":"ZSXZ","airport":"徐州观音机场","enname":"XUZHOU","areaen":"xuzhou","simpleen":"xz","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"147"}],"Y":[{"id":"122","areaname":"延安","sajm":"ENY","country":"中国","cjm":"CN","sijm":"ZLYA","airport":"延安二十里铺机场","enname":"YANAN","areaen":"yanan","simpleen":"ya","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"148"},{"id":"71","areaname":"盐城","sajm":"YNZ","country":"中国","cjm":"CN","sijm":"ZSYN","airport":"江苏盐城南洋机场","enname":"YANCHENG","areaen":"yancheng","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"149"},{"id":"119","areaname":"延吉","sajm":"YNJ","country":"中国","cjm":"CN","sijm":"ZYYJ","airport":"延吉朝阳川机场","enname":"YANJI","areaen":"yanji","simpleen":"yj","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"150"},{"id":"54","areaname":"烟台","sajm":"YNT","country":"中国","cjm":"CN","sijm":"ZSYT","airport":"烟台莱山机场","enname":"YANTAI","areaen":"yantai","simpleen":"yt","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"151"},{"id":"58","areaname":"宜宾","sajm":"YBP","country":"中国","cjm":"CN","sijm":"ZUYB","airport":"宜宾(菜坝)机场","enname":"YIBIN","areaen":"yibin","simpleen":"yb","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"152"},{"id":"127","areaname":"宜昌","sajm":"YIH","country":"中国","cjm":"CN","sijm":"ZHYC","airport":"宜昌三峡机场","enname":"YICHANG","areaen":"yichang","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"153"},{"id":"39","areaname":"银川","sajm":"INC","country":"中国","cjm":"CN","sijm":"ZLIC","airport":"银川河东机场","enname":"YINCHUAN","areaen":"yinchuan","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"154"},{"id":"159","areaname":"伊宁","sajm":"YIN","country":"中国","cjm":"CN","sijm":"ZWYN","airport":"伊宁机场","enname":"YINING","areaen":"yining","simpleen":"yn","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"155"},{"id":"57","areaname":"义乌","sajm":"YIW","country":"中国","cjm":"CN","sijm":"ZSYW","airport":"义乌机场","enname":"YIWU","areaen":"yiwu","simpleen":"yw","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"156"},{"id":"155","areaname":"榆林","sajm":"UYN","country":"中国","cjm":"CN","sijm":"ZLYL","airport":"榆林西沙机场","enname":"YULIN","areaen":"yulin","simpleen":"yl","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"157"},{"id":"167","areaname":"运城","sajm":"YCU","country":"中国","cjm":"CN","sijm":"ZBYC","airport":"运城关公机场","enname":"YUNCHENG","areaen":"yuncheng","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"158"}],"Z":[{"id":"111","areaname":"张家界","sajm":"DYG","country":"中国","cjm":"CN","sijm":"ZGDY","airport":"张家界荷花机场","enname":"ZHANGJIAJIE","areaen":"zhangjiajie","simpleen":"zjj","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"159"},{"id":"31","areaname":"湛江","sajm":"ZHA","country":"中国","cjm":"CN","sijm":"ZGZJ","airport":"湛江机场","enname":"ZHANJIANG","areaen":"zhanjiang","simpleen":"zj","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"160"},{"id":"140","areaname":"昭通","sajm":"ZAT","country":"中国","cjm":"CN","sijm":"ZPZT","airport":"昭通机场","enname":"ZHAOTONG","areaen":"zhaotong","simpleen":"zt","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"161"},{"id":"55","areaname":"郑州","sajm":"CGO","country":"中国","cjm":"CN","sijm":"ZHCC","airport":"郑州新郑机场","enname":"ZHENGZHOU","areaen":"zhengzhou","simpleen":"zz","jianpin":"Z","ishot":"1","status":"1","remark":"","row_number":"162"},{"id":"146","areaname":"舟山","sajm":"HSN","country":"中国","cjm":"CN","sijm":"ZSZS","airport":"舟山普陀山朱家尖机场","enname":"ZHOUSHAN","areaen":"zhoushan","simpleen":"zs","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"163"},{"id":"44","areaname":"珠海","sajm":"ZUH","country":"中国","cjm":"CN","sijm":"ZGSD","airport":"珠海三灶机场","enname":"ZHUHAI","areaen":"zhuhai","simpleen":"zh","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"164"},{"id":"42","areaname":"遵义","sajm":"ZYI","country":"中国","cjm":"CN","sijm":"ZUZY","airport":"遵义机场","enname":"ZUNYI","areaen":"zunyi","simpleen":"zy","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"165"}]}}
     * msg : 数据获取成功
     */

    private int status;
    private DataBean data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * hot : [{"id":"1","areaname":"上海","sajm":"SHA","country":"中国","cjm":"CN","sijm":"ZSSS","airport":"上海虹桥机场","enname":"SHANGHAI","areaen":"shanghai","simpleen":"sh","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"1"},{"id":"2","areaname":"北京","sajm":"PEK","country":"中国","cjm":"CN","sijm":"ZBAA","airport":"北京首都机场","enname":"BEIJING","areaen":"beijing","simpleen":"bj","jianpin":"B","ishot":"1","status":"1","remark":"","row_number":"2"},{"id":"3","areaname":"广州","sajm":"CAN","country":"中国","cjm":"CN","sijm":"ZGGG","airport":"广州白云机场","enname":"GUANGZHOU","areaen":"guangzhou","simpleen":"gz","jianpin":"G","ishot":"1","status":"1","remark":"","row_number":"3"},{"id":"4","areaname":"深圳","sajm":"SZX","country":"中国","cjm":"CN","sijm":"ZGSZ","airport":"深圳宝安机场","enname":"SHENZHEN","areaen":"shenzhen","simpleen":"sz","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"4"},{"id":"7","areaname":"天津","sajm":"TSN","country":"中国","cjm":"CN","sijm":"ZBTJ","airport":"天津滨海机场","enname":"TIANJIN","areaen":"tianjin","simpleen":"tj","jianpin":"T","ishot":"1","status":"1","remark":"","row_number":"5"},{"id":"9","areaname":"杭州","sajm":"HGH","country":"中国","cjm":"CN","sijm":"ZSHC","airport":"杭州萧山机场","enname":"HANGZHOU","areaen":"hangzhou","simpleen":"hj","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"6"},{"id":"10","areaname":"重庆","sajm":"CKG","country":"中国","cjm":"CN","sijm":"ZUCK","airport":"重庆江北机场","enname":"CHONGQING","areaen":"chongqing","simpleen":"cq","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"7"},{"id":"12","areaname":"成都","sajm":"CTU","country":"中国","cjm":"CN","sijm":"ZUUU","airport":"成都双流机场","enname":"CHENGDU","areaen":"chengdu","simpleen":"cd","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"8"},{"id":"13","areaname":"青岛","sajm":"TAO","country":"中国","cjm":"CN","sijm":"ZSQD","airport":"青岛流亭机场","enname":"QINGDAO","areaen":"qingdao","simpleen":"qd","jianpin":"Q","ishot":"1","status":"1","remark":"","row_number":"9"},{"id":"14","areaname":"西安","sajm":"XIY","country":"中国","cjm":"CN","sijm":"ZLXY","airport":"西安咸阳机场","enname":"XIAN","areaen":"xian","simpleen":"xa","jianpin":"X","ishot":"1","status":"1","remark":"","row_number":"10"},{"id":"19","areaname":"大连","sajm":"DLC","country":"中国","cjm":"CN","sijm":"ZYTL","airport":"大连国际机场","enname":"DALIAN","areaen":"dalian","simpleen":"dl","jianpin":"D","ishot":"1","status":"1","remark":"","row_number":"11"},{"id":"25","areaname":"福州","sajm":"FOC","country":"中国","cjm":"CN","sijm":"ZSFZ","airport":"福州长乐机场","enname":"FUZHOU","areaen":"fuzhou","simpleen":"fz","jianpin":"F","ishot":"1","status":"1","remark":"","row_number":"12"},{"id":"32","areaname":"长沙","sajm":"CSX","country":"中国","cjm":"CN","sijm":"ZGHA","airport":"长沙黄花机场","enname":"CHANGSHA","areaen":"changsha","simpleen":"cs","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"13"},{"id":"33","areaname":"三亚","sajm":"SYX","country":"中国","cjm":"CN","sijm":"ZJSY","airport":"三亚凤凰机场","enname":"SANYA","areaen":"sanya","simpleen":"sy","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"14"},{"id":"53","areaname":"海口","sajm":"HAK","country":"中国","cjm":"CN","sijm":"ZJHK","airport":"海口美兰机场","enname":"HAIKOU","areaen":"haikou","simpleen":"hk","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"15"},{"id":"55","areaname":"郑州","sajm":"CGO","country":"中国","cjm":"CN","sijm":"ZHCC","airport":"郑州新郑机场","enname":"ZHENGZHOU","areaen":"zhengzhou","simpleen":"zz","jianpin":"Z","ishot":"1","status":"1","remark":"","row_number":"16"}]
         * other : {"A":[{"id":"43","areaname":"阿克苏","sajm":"AKU","country":"中国","cjm":"CN","sijm":"ZWAK","airport":"阿克苏机场","enname":"AKSU","areaen":"aksu","simpleen":"aks","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"1"},{"id":"38","areaname":"阿勒泰","sajm":"AAT","country":"中国","cjm":"CN","sijm":"ZWAT","airport":"阿勒泰机场","enname":"ALTAY","areaen":"aletai","simpleen":"alt","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"2"},{"id":"45","areaname":"安康","sajm":"AKA","country":"中国","cjm":"CN","sijm":"ZLAK","airport":"安康机场","enname":"ANKANG","areaen":"ankang","simpleen":"ak","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"3"},{"id":"69","areaname":"安庆","sajm":"AQG","country":"中国","cjm":"CN","sijm":"ZSAQ","airport":"安庆(天柱山）机场","enname":"ANQING","areaen":"anqing","simpleen":"aq","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"4"},{"id":"109","areaname":"鞍山","sajm":"AOG","country":"中国","cjm":"CN","sijm":"ZYAS","airport":"鞍山机场","enname":"ANSHAN","areaen":"anshan","simpleen":"as","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"5"},{"id":"91","areaname":"安阳","sajm":"AYN","country":"中国","cjm":"CN","sijm":"ZHAY","airport":"安阳北郊机场","enname":"ANYANG","areaen":"anyang","simpleen":"ay","jianpin":"A","ishot":"","status":"1","remark":"","row_number":"6"}],"B":[{"id":"98","areaname":"保山","sajm":"BSD","country":"中国","cjm":"CN","sijm":"ZPBS","airport":"保山云瑞机场","enname":"BAOSHAN","areaen":"baoshan","simpleen":"bs","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"7"},{"id":"96","areaname":"包头","sajm":"BAV","country":"中国","cjm":"CN","sijm":"ZBOW","airport":"包头二里半机场","enname":"BAOTOU","areaen":"baotou","simpleen":"bt","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"8"},{"id":"40","areaname":"北海","sajm":"BHY","country":"中国","cjm":"CN","sijm":"ZGBH","airport":"北海福城机场","enname":"BEIHAI","areaen":"beihai","simpleen":"bh","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"9"},{"id":"2","areaname":"北京","sajm":"PEK","country":"中国","cjm":"CN","sijm":"ZBAA","airport":"北京首都机场","enname":"BEIJING","areaen":"beijing","simpleen":"bj","jianpin":"B","ishot":"1","status":"1","remark":"","row_number":"10"},{"id":"90","areaname":"蚌埠","sajm":"BFU","country":"中国","cjm":"CN","sijm":"ZSBB","airport":"蚌埠机场","enname":"BENGBU","areaen":"bengbu","simpleen":"bb","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"11"},{"id":"166","areaname":"博鳌","sajm":"BAR","country":"中国","cjm":"CN","sijm":"ZJQH","airport":"博鳌国际机场","enname":"BOAO","areaen":"boao","simpleen":"ba","jianpin":"B","ishot":"","status":"1","remark":"","row_number":"12"}],"C":[{"id":"26","areaname":"长春","sajm":"CGQ","country":"中国","cjm":"CN","sijm":"ZYCC","airport":"长春龙嘉国际机场","enname":"CHANGCHUN","areaen":"changchun","simpleen":"cc","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"13"},{"id":"93","areaname":"常德","sajm":"CGD","country":"中国","cjm":"CN","sijm":"ZGCD","airport":"常德桃花源(斗姆湖)机场","enname":"CHANGDE","areaen":"changde","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"14"},{"id":"92","areaname":"昌都","sajm":"BPX","country":"中国","cjm":"CN","sijm":"ZUBD","airport":"西藏昌都邦达机场","enname":"CHAMDO","areaen":"changdu","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"15"},{"id":"62","areaname":"昌都","sajm":"BPX","country":"中国","cjm":"CN","sijm":"ZUBD","airport":"昌都邦达机场","enname":"BARDUFOSS","areaen":"changdu","simpleen":"cd","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"16"},{"id":"106","areaname":"长海","sajm":"CNI","country":"中国","cjm":"CN","sijm":"ZYCH","airport":"长海（大长山岛）机场","enname":"DALIAN","areaen":"changhai","simpleen":"ch","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"17"},{"id":"32","areaname":"长沙","sajm":"CSX","country":"中国","cjm":"CN","sijm":"ZGHA","airport":"长沙黄花机场","enname":"CHANGSHA","areaen":"changsha","simpleen":"cs","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"18"},{"id":"95","areaname":"长治","sajm":"CIH","country":"中国","cjm":"CN","sijm":"ZBCZ","airport":"长治王村机场","enname":"CHANGZHI","areaen":"changzhi","simpleen":"cz","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"19"},{"id":"46","areaname":"常州","sajm":"CZX","country":"中国","cjm":"CN","sijm":"ZSCG","airport":"常州奔牛机场","enname":"CHANGZHOU","areaen":"changzhou","simpleen":"cz","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"20"},{"id":"99","areaname":"朝阳","sajm":"CHG","country":"中国","cjm":"CN","sijm":"ZYCY","airport":"辽宁朝阳机场","enname":"CHAOYANG","areaen":"chaoyang","simpleen":"cy","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"21"},{"id":"12","areaname":"成都","sajm":"CTU","country":"中国","cjm":"CN","sijm":"ZUUU","airport":"成都双流机场","enname":"CHENGDU","areaen":"chengdu","simpleen":"cd","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"22"},{"id":"101","areaname":"赤峰","sajm":"CIF","country":"中国","cjm":"CN","sijm":"ZBCF","airport":"赤峰玉龙机场","enname":"CHIFENG","areaen":"chifeng","simpleen":"cf","jianpin":"C","ishot":"","status":"1","remark":"","row_number":"23"},{"id":"10","areaname":"重庆","sajm":"CKG","country":"中国","cjm":"CN","sijm":"ZUCK","airport":"重庆江北机场","enname":"CHONGQING","areaen":"chongqing","simpleen":"cq","jianpin":"C","ishot":"1","status":"1","remark":"","row_number":"24"}],"D":[{"id":"48","areaname":"大理","sajm":"DLU","country":"中国","cjm":"CN","sijm":"ZPDL","airport":"大理荒草坝机场","enname":"DALIXIAGUAN","areaen":"dali","simpleen":"dl","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"25"},{"id":"19","areaname":"大连","sajm":"DLC","country":"中国","cjm":"CN","sijm":"ZYTL","airport":"大连国际机场","enname":"DALIAN","areaen":"dalian","simpleen":"dl","jianpin":"D","ishot":"1","status":"1","remark":"","row_number":"26"},{"id":"107","areaname":"丹东","sajm":"DDG","country":"中国","cjm":"CN","sijm":"ZYDD","airport":"丹东浪头机场","enname":"DANDONG","areaen":"dandong","simpleen":"dd","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"27"},{"id":"105","areaname":"大庆","sajm":"DQA","country":"中国","cjm":"CN","sijm":"ZYDQ","airport":"大庆萨尔图机场","enname":"DAQING","areaen":"daqing","simpleen":"dq","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"28"},{"id":"102","areaname":"大同","sajm":"DAT","country":"中国","cjm":"CN","sijm":"ZBDT","airport":"山西大同机场","enname":"DATONG","areaen":"datong","simpleen":"dt","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"29"},{"id":"103","areaname":"达州","sajm":"DAX","country":"中国","cjm":"CN","sijm":"ZUDX","airport":"达州(河市)机场","enname":"DAZHOU","areaen":"dazhou","simpleen":"dz","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"30"},{"id":"104","areaname":"迪庆","sajm":"DIG","country":"中国","cjm":"CN","sijm":"ZPDQ","airport":"迪庆香格里拉机场","enname":"DIQING","areaen":"diqing","simpleen":"dq","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"31"},{"id":"77","areaname":"敦煌","sajm":"DNH","country":"中国","cjm":"CN","sijm":"ZLDH","airport":"敦煌机场","enname":"DUNHUANG","areaen":"dunhuang","simpleen":"dh","jianpin":"D","ishot":"","status":"1","remark":"","row_number":"32"}],"E":[{"id":"37","areaname":"鄂尔多斯","sajm":"DSN","country":"中国","cjm":"CN","sijm":"ZBDS","airport":"鄂尔多斯伊金霍洛机场","enname":"ORDOS","areaen":"eerduosi","simpleen":"eeds","jianpin":"E","ishot":"","status":"1","remark":"","row_number":"33"},{"id":"112","areaname":"恩施","sajm":"ENH","country":"中国","cjm":"CN","sijm":"ZHES","airport":"恩施机场","enname":"ENSHI","areaen":"enshi","simpleen":"es","jianpin":"E","ishot":"","status":"1","remark":"","row_number":"34"}],"F":[{"id":"113","areaname":"阜阳","sajm":"FUG","country":"中国","cjm":"CN","sijm":"ZSFY","airport":"阜阳西关机场","enname":"FUYANG","areaen":"fuyang","simpleen":"fy","jianpin":"F","ishot":"","status":"1","remark":"","row_number":"35"},{"id":"100","areaname":"富蕴","sajm":"FYN","country":"中国","cjm":"CN","sijm":"ZWFY","airport":"富蕴机场","enname":"FUYUN","areaen":"fuyun","simpleen":"fy","jianpin":"F","ishot":"","status":"1","remark":"","row_number":"36"},{"id":"25","areaname":"福州","sajm":"FOC","country":"中国","cjm":"CN","sijm":"ZSFZ","airport":"福州长乐机场","enname":"FUZHOU","areaen":"fuzhou","simpleen":"fz","jianpin":"F","ishot":"1","status":"1","remark":"","row_number":"37"}],"G":[{"id":"67","areaname":"赣州","sajm":"KOW","country":"中国","cjm":"CN","sijm":"ZSGZ","airport":"赣州黄金机场","enname":"GANZHOU","areaen":"ganzhou","simpleen":"gz","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"38"},{"id":"8","areaname":"高雄","sajm":"KHH","country":"中国","cjm":"CN","sijm":"RCKH","airport":"高雄机场","enname":"GAOXIONG","areaen":"gaoxiong","simpleen":"gx","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"39"},{"id":"68","areaname":"格尔木","sajm":"GOQ","country":"中国","cjm":"CN","sijm":"ZLGM","airport":"格尔木机场","enname":"GOLMUD","areaen":"geermu","simpleen":"gem","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"40"},{"id":"70","areaname":"广汉","sajm":"GHN","country":"中国","cjm":"CN","sijm":"ZUGH","airport":"广汉机场","enname":"GUANGHAN","areaen":"guanghan","simpleen":"gh","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"41"},{"id":"3","areaname":"广州","sajm":"CAN","country":"中国","cjm":"CN","sijm":"ZGGG","airport":"广州白云机场","enname":"GUANGZHOU","areaen":"guangzhou","simpleen":"gz","jianpin":"G","ishot":"1","status":"1","remark":"","row_number":"42"},{"id":"72","areaname":"桂林","sajm":"KWL","country":"中国","cjm":"CN","sijm":"ZGKL","airport":"桂林两江机场","enname":"GUILIN","areaen":"guilin","simpleen":"gl","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"43"},{"id":"28","areaname":"贵阳","sajm":"KWE","country":"中国","cjm":"CN","sijm":"ZUGY","airport":"贵阳龙洞堡机场","enname":"GUIYANG","areaen":"guiyang","simpleen":"gy","jianpin":"G","ishot":"","status":"1","remark":"","row_number":"44"}],"H":[{"id":"18","areaname":"哈尔滨","sajm":"HRB","country":"中国","cjm":"CN","sijm":"ZYHB","airport":"哈尔滨太平机场","enname":"HARBIN","areaen":"haerbin","simpleen":"hrb","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"45"},{"id":"53","areaname":"海口","sajm":"HAK","country":"中国","cjm":"CN","sijm":"ZJHK","airport":"海口美兰机场","enname":"HAIKOU","areaen":"haikou","simpleen":"hk","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"46"},{"id":"88","areaname":"海拉尔","sajm":"HLD","country":"中国","cjm":"CN","sijm":"ZBLA","airport":"内蒙古海拉尔东山机场","enname":"HAILAR","areaen":"hailaer","simpleen":"hle","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"47"},{"id":"76","areaname":"哈密","sajm":"HMI","country":"中国","cjm":"CN","sijm":"ZWHM","airport":"哈密机场","enname":"HAMI","areaen":"hami","simpleen":"hm","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"48"},{"id":"9","areaname":"杭州","sajm":"HGH","country":"中国","cjm":"CN","sijm":"ZSHC","airport":"杭州萧山机场","enname":"HANGZHOU","areaen":"hangzhou","simpleen":"hj","jianpin":"H","ishot":"1","status":"1","remark":"","row_number":"49"},{"id":"79","areaname":"汉中","sajm":"HZG","country":"中国","cjm":"CN","sijm":"ZLHZ","airport":"汉中西关机场","enname":"HANZHONG","areaen":"hanzhong","simpleen":"hz","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"50"},{"id":"80","areaname":"合肥","sajm":"HFE","country":"中国","cjm":"CN","sijm":"ZSOF","airport":"合肥骆岗机场","enname":"HEFEI","areaen":"hefei","simpleen":"hf","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"51"},{"id":"81","areaname":"黑河","sajm":"HEK","country":"中国","cjm":"CN","sijm":"ZYHE","airport":"黑河机场","enname":"HEIHE","areaen":"heihe","simpleen":"hh","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"52"},{"id":"82","areaname":"衡阳","sajm":"HNY","country":"中国","cjm":"CN","sijm":"ZGHY","airport":"衡阳东江机场","enname":"HENGYANG","areaen":"hengyang","simpleen":"hy","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"53"},{"id":"83","areaname":"和田","sajm":"HTN","country":"中国","cjm":"CN","sijm":"ZWTN","airport":"和田机场","enname":"HETIAN","areaen":"hetian","simpleen":"ht","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"54"},{"id":"73","areaname":"怀化","sajm":"HJJ","country":"中国","cjm":"CN","sijm":"ZGCJ","airport":"怀化芷江机场","enname":"HUAIHUA","areaen":"huaihua","simpleen":"hh","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"55"},{"id":"66","areaname":"花莲","sajm":"HUN","country":"中国","cjm":"CN","sijm":"RCYU","airport":"花莲机场","enname":"HELENA","areaen":"hualian","simpleen":"hl","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"56"},{"id":"84","areaname":"黄山","sajm":"TXN","country":"中国","cjm":"CN","sijm":"ZSTX","airport":"黄山屯溪机场","enname":"HUANGSHAN","areaen":"huangshan","simpleen":"hs","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"57"},{"id":"23","areaname":"呼和浩特","sajm":"HET","country":"中国","cjm":"CN","sijm":"ZBHH","airport":"呼和浩特白塔国际机场","enname":"HOHHOT","areaen":"huhehaote","simpleen":"hhht","jianpin":"H","ishot":"","status":"1","remark":"","row_number":"58"}],"J":[{"id":"87","areaname":"佳木斯","sajm":"JMU","country":"中国","cjm":"CN","sijm":"ZYJM","airport":"佳木斯机场","enname":"JIAMUSI","areaen":"jiamusi","simpleen":"jms","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"59"},{"id":"97","areaname":"吉安","sajm":"KNC","country":"中国","cjm":"CN","sijm":"ZSJA","airport":"吉安机场","enname":"JIAN","areaen":"jian","simpleen":"ja","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"60"},{"id":"86","areaname":"嘉义","sajm":"CYI","country":"中国","cjm":"CN","sijm":"RCKU","airport":"嘉义机场","enname":"JIAYI","areaen":"jiayi","simpleen":"jy","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"61"},{"id":"110","areaname":"嘉峪关","sajm":"JGN","country":"中国","cjm":"CN","sijm":"ZLJQ","airport":"嘉峪关机场","enname":"JIAYUGUAN","areaen":"jiayuguan","simpleen":"jyg","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"62"},{"id":"139","areaname":"揭阳","sajm":"SWA","country":"中国","cjm":"CN","sijm":"ZGOW","airport":"揭阳潮汕机场","enname":"JIEYANG","areaen":"jieyang","simpleen":"jy","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"63"},{"id":"153","areaname":"吉林","sajm":"JIL","country":"中国","cjm":"CN","sijm":"ZYJL","airport":"吉林二台子机场","enname":"JILIN","areaen":"jilin","simpleen":"jl","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"64"},{"id":"49","areaname":"济南","sajm":"TNA","country":"中国","cjm":"CN","sijm":"ZSJN","airport":"济南遥墙机场","enname":"JINAN","areaen":"jinan","simpleen":"jn","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"65"},{"id":"143","areaname":"景德镇","sajm":"JDZ","country":"中国","cjm":"CN","sijm":"ZSJD","airport":"景德镇罗家机场","enname":"JINGDEZHEN","areaen":"jingdezhen","simpleen":"jdz","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"66"},{"id":"115","areaname":"济宁","sajm":"JNG","country":"中国","cjm":"CN","sijm":"ZSJG","airport":"济宁机场","enname":"JINING","areaen":"jining","simpleen":"jn","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"67"},{"id":"145","areaname":"晋江","sajm":"JJN","country":"中国","cjm":"CN","sijm":"ZSQZ","airport":"泉州晋江机场","enname":"QUANZHOU","areaen":"jinjang","simpleen":"jj","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"68"},{"id":"30","areaname":"锦州","sajm":"JNZ","country":"中国","cjm":"CN","sijm":"ZYJZ","airport":"锦州小岭子机场","enname":"JINZHOU","areaen":"jinzhou","simpleen":"jz","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"69"},{"id":"148","areaname":"九江","sajm":"JIU","country":"中国","cjm":"CN","sijm":"ZSJJ","airport":"九江庐山机场","enname":"JIUJIANG","areaen":"jiujiang","simpleen":"jj","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"70"},{"id":"147","areaname":"酒泉","sajm":"CHW","country":"中国","cjm":"CN","sijm":"NNNN","airport":"酒泉机场","enname":"JIUQUAN","areaen":"jiuquan","simpleen":"jq","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"71"},{"id":"78","areaname":"九寨沟","sajm":"JZH","country":"中国","cjm":"CN","sijm":"ZUJZ","airport":"九寨沟黄龙(九黄）机场","enname":"JIUZHAIGOU","areaen":"jiuzhaigou","simpleen":"jzg","jianpin":"J","ishot":"","status":"1","remark":"","row_number":"72"}],"K":[{"id":"150","areaname":"喀什","sajm":"KHG","country":"中国","cjm":"CN","sijm":"ZWSH","airport":"喀什机场","enname":"KASHGAR","areaen":"kashi","simpleen":"ks","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"73"},{"id":"149","areaname":"克拉玛依","sajm":"KRY","country":"中国","cjm":"CN","sijm":"ZWKM","airport":"克拉玛依机场","enname":"KARAMAY","areaen":"kelamayi","simpleen":"klmy","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"74"},{"id":"152","areaname":"库车","sajm":"KCA","country":"中国","cjm":"CN","sijm":"ZWKC","airport":"库车机场","enname":"KUQA","areaen":"kuche","simpleen":"kc","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"75"},{"id":"165","areaname":"库尔勒","sajm":"KRL","country":"中国","cjm":"CN","sijm":"ZWKL","airport":"库尔勒机场","enname":"KORLA","areaen":"kuerle","simpleen":"kel","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"76"},{"id":"15","areaname":"昆明","sajm":"KMG","country":"中国","cjm":"CN","sijm":"ZPPP","airport":"昆明巫家坝机场","enname":"KUNMING","areaen":"kunming","simpleen":"km","jianpin":"K","ishot":"","status":"1","remark":"","row_number":"77"}],"L":[{"id":"22","areaname":"兰州","sajm":"LHW","country":"中国","cjm":"CN","sijm":"ZLLL","airport":"兰州机场","enname":"LANZHOU","areaen":"lanzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"78"},{"id":"41","areaname":"拉萨","sajm":"LXA","country":"中国","cjm":"CN","sijm":"ZULS","airport":"拉萨贡嘎机场","enname":"LHASA","areaen":"lasa","simpleen":"ls","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"79"},{"id":"156","areaname":"连云港","sajm":"LYG","country":"中国","cjm":"CN","sijm":"ZSLG","airport":"连云港白塔埠机场","enname":"LIANYUNGANG","areaen":"lianyungang","simpleen":"lyg","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"80"},{"id":"157","areaname":"丽江","sajm":"LJG","country":"中国","cjm":"CN","sijm":"ZPLJ","airport":"丽江三义机场","enname":"LIJIANG","areaen":"lijiang","simpleen":"lj","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"81"},{"id":"65","areaname":"丽江","sajm":"LJG","country":"中国","cjm":"CN","sijm":"ZPLJ","airport":"丽江三义机场","enname":"LIJIANG","areaen":"lijiang","simpleen":"lj","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"82"},{"id":"154","areaname":"临沧","sajm":"LNJ","country":"中国","cjm":"CN","sijm":"ZPLC","airport":"临沧机场","enname":"LINCANG","areaen":"lincang","simpleen":"lc","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"83"},{"id":"158","areaname":"临沂","sajm":"LYI","country":"中国","cjm":"CN","sijm":"ZSLY","airport":"临沂沭埠岭机场","enname":"LINYI","areaen":"linyi","simpleen":"ly","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"84"},{"id":"50","areaname":"柳州","sajm":"LZH","country":"中国","cjm":"CN","sijm":"ZGZH","airport":"柳州白莲机场","enname":"LIUZHOU","areaen":"liuzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"85"},{"id":"51","areaname":"洛阳","sajm":"LYA","country":"中国","cjm":"CN","sijm":"ZHLY","airport":"洛阳机场","enname":"LUOYANG","areaen":"luoyang","simpleen":"ly","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"86"},{"id":"160","areaname":"泸州","sajm":"LZO","country":"中国","cjm":"CN","sijm":"ZULZ","airport":"泸州蓝田机场","enname":"LUZHOU","areaen":"luzhou","simpleen":"lz","jianpin":"L","ishot":"","status":"1","remark":"","row_number":"87"}],"M":[{"id":"161","areaname":"芒市","sajm":"LUM","country":"中国","cjm":"CN","sijm":"ZPMS","airport":"芒市机场","enname":"MANGSHI","areaen":"mangshi","simpleen":"ms","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"88"},{"id":"162","areaname":"梅县","sajm":"MXZ","country":"中国","cjm":"CN","sijm":"ZGMX","airport":"梅县机场","enname":"MEIXIAN","areaen":"meixian","simpleen":"mx","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"89"},{"id":"164","areaname":"绵阳","sajm":"MIG","country":"中国","cjm":"CN","sijm":"ZUMY","airport":"绵阳南郊机场","enname":"MIANYANG","areaen":"mianyang","simpleen":"my","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"90"},{"id":"163","areaname":"牡丹江","sajm":"MDG","country":"中国","cjm":"CN","sijm":"ZYMD","airport":"牡丹江海浪机场","enname":"MUDANJIANG","areaen":"mudanjiang","simpleen":"mdj","jianpin":"M","ishot":"","status":"1","remark":"","row_number":"91"}],"N":[{"id":"74","areaname":"那拉提","sajm":"NLT","country":"中国","cjm":"CN","sijm":"ZWNL","airport":"那拉提机场","enname":"NALATI","areaen":"nalati","simpleen":"nlt","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"92"},{"id":"27","areaname":"南昌","sajm":"KHN","country":"中国","cjm":"CN","sijm":"ZSCN","airport":"南昌昌北机场","enname":"NANCHANG","areaen":"nanchang","simpleen":"nc","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"93"},{"id":"125","areaname":"南充","sajm":"NAO","country":"中国","cjm":"CN","sijm":"ZUNC","airport":"南充高坪机场","enname":"NANCHONG","areaen":"nanchong","simpleen":"nc","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"94"},{"id":"17","areaname":"南京","sajm":"NKG","country":"中国","cjm":"CN","sijm":"ZSNJ","airport":"南京禄口机场","enname":"NANJING","areaen":"nanjing","simpleen":"nj","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"95"},{"id":"52","areaname":"南宁","sajm":"NNG","country":"中国","cjm":"CN","sijm":"ZGNN","airport":"南宁吴圩机场","enname":"NANNING","areaen":"nanning","simpleen":"nn","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"96"},{"id":"142","areaname":"南通","sajm":"NTG","country":"中国","cjm":"CN","sijm":"ZSNT","airport":"南通兴东机场","enname":"NANTONG","areaen":"nantong","simpleen":"nt","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"97"},{"id":"151","areaname":"南阳","sajm":"NNY","country":"中国","cjm":"CN","sijm":"ZHNY","airport":"南阳姜营机场","enname":"NANYANG","areaen":"nanyang","simpleen":"ny","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"98"},{"id":"24","areaname":"南苑","sajm":"NAY","country":"中国","cjm":"CN","sijm":"ZBNY","airport":"北京南苑机场","enname":"NANYUAN","areaen":"nanyuan","simpleen":"ny","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"99"},{"id":"117","areaname":"宁波","sajm":"NGB","country":"中国","cjm":"CN","sijm":"ZSNB","airport":"宁波栎社机场","enname":"NINGBO","areaen":"ningbo","simpleen":"nb","jianpin":"N","ishot":"","status":"1","remark":"","row_number":"100"}],"P":[{"id":"6","areaname":"浦东","sajm":"PVG","country":"中国","cjm":"CN","sijm":"ZSPD","airport":"上海浦东机场","enname":"PUDONG","areaen":"pudong","simpleen":"pd","jianpin":"P","ishot":"","status":"1","remark":"","row_number":"101"}],"Q":[{"id":"118","areaname":"且末","sajm":"IQM","country":"中国","cjm":"CN","sijm":"ZWCM","airport":"且末机场","enname":"QIEMO","areaen":"qiemo","simpleen":"qm","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"102"},{"id":"13","areaname":"青岛","sajm":"TAO","country":"中国","cjm":"CN","sijm":"ZSQD","airport":"青岛流亭机场","enname":"QINGDAO","areaen":"qingdao","simpleen":"qd","jianpin":"Q","ishot":"1","status":"1","remark":"","row_number":"103"},{"id":"120","areaname":"庆阳","sajm":"IQN","country":"中国","cjm":"CN","sijm":"ZLQY","airport":"庆阳机场","enname":"QINGYANG","areaen":"qingyang","simpleen":"qy","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"104"},{"id":"121","areaname":"秦皇岛","sajm":"SHP","country":"中国","cjm":"CN","sijm":"ZBSH","airport":"秦皇岛山海关机场","enname":"QINHUANGDAO","areaen":"qinhuangdao","simpleen":"qhd","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"105"},{"id":"35","areaname":"齐齐哈尔","sajm":"NDG","country":"中国","cjm":"CN","sijm":"ZYQQ","airport":"齐齐哈尔三家子机场","enname":"QIQIHAER","areaen":"qiqihaer","simpleen":"qqhe","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"106"},{"id":"123","areaname":"衢州","sajm":"JUZ","country":"中国","cjm":"CN","sijm":"ZSJU","airport":"衢州机场","enname":"QUZHOU","areaen":"quzhou","simpleen":"qz","jianpin":"Q","ishot":"","status":"1","remark":"","row_number":"107"}],"S":[{"id":"33","areaname":"三亚","sajm":"SYX","country":"中国","cjm":"CN","sijm":"ZJSY","airport":"三亚凤凰机场","enname":"SANYA","areaen":"sanya","simpleen":"sy","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"108"},{"id":"1","areaname":"上海","sajm":"SHA","country":"中国","cjm":"CN","sijm":"ZSSS","airport":"上海虹桥机场","enname":"SHANGHAI","areaen":"shanghai","simpleen":"sh","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"109"},{"id":"89","areaname":"韶关","sajm":"HSC","country":"中国","cjm":"CN","sijm":"","airport":"韶关机场","enname":"SHAOGUAN","areaen":"shaoguan","simpleen":"sg","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"110"},{"id":"126","areaname":"沙市","sajm":"SHS","country":"中国","cjm":"CN","sijm":"ZHSS","airport":"荆州沙市机场","enname":"SHASHI","areaen":"shashi","simpleen":"ss","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"111"},{"id":"20","areaname":"沈阳","sajm":"SHE","country":"中国","cjm":"CN","sijm":"ZYTX","airport":"沈阳桃仙机场","enname":"SHENYANG","areaen":"shenyang","simpleen":"sy","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"112"},{"id":"4","areaname":"深圳","sajm":"SZX","country":"中国","cjm":"CN","sijm":"ZGSZ","airport":"深圳宝安机场","enname":"SHENZHEN","areaen":"shenzhen","simpleen":"sz","jianpin":"S","ishot":"1","status":"1","remark":"","row_number":"113"},{"id":"128","areaname":"石家庄","sajm":"SJW","country":"中国","cjm":"CN","sijm":"ZBSJ","airport":"石家庄正定机场","enname":"SHIJIAZHUANG","areaen":"shijiazhuang","simpleen":"sjz","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"114"},{"id":"129","areaname":"思茅","sajm":"SYM","country":"中国","cjm":"CN","sijm":"ZPSM","airport":"普洱(思茅)机场","enname":"PUER","areaen":"simao","simpleen":"sm","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"115"},{"id":"47","areaname":"苏州","sajm":"SZV","country":"中国","cjm":"CN","sijm":"ZSSZ","airport":"苏州机场","enname":"SUZHOU","areaen":"suzhou","simpleen":"sz","jianpin":"S","ishot":"","status":"1","remark":"","row_number":"116"}],"T":[{"id":"131","areaname":"塔城","sajm":"TCG","country":"中国","cjm":"CN","sijm":"ZWTC","airport":"塔城机场","enname":"TACHENG","areaen":"tacheng","simpleen":"tc","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"117"},{"id":"5","areaname":"台北","sajm":"TSA","country":"中国","cjm":"CN","sijm":"RCSS","airport":"台北松山机场","enname":"TAIBEI","areaen":"taibei","simpleen":"tb","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"118"},{"id":"141","areaname":"台东","sajm":"TTG","country":"中国","cjm":"CN","sijm":"RCFN","airport":"台东志航机场","enname":"TAIDONG","areaen":"taidong","simpleen":"td","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"119"},{"id":"108","areaname":"台南","sajm":"TNN","country":"中国","cjm":"CN","sijm":"RCNN","airport":"台南机场","enname":"TAINAN","areaen":"tainan","simpleen":"tn","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"120"},{"id":"34","areaname":"太原","sajm":"TYN","country":"中国","cjm":"CN","sijm":"ZBYN","airport":"太原武宿机场","enname":"TAIYUAN","areaen":"taiyuan","simpleen":"ty","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"121"},{"id":"64","areaname":"台中","sajm":"RMQ","country":"中国","cjm":"CN","sijm":"RCMQ","airport":"台中清泉岗机场","enname":"TAICHUNG","areaen":"taizhong","simpleen":"tz","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"122"},{"id":"85","areaname":"台州","sajm":"HYN","country":"中国","cjm":"CN","sijm":"ZSLQ","airport":"台州路桥（原黄岩路桥）机场","enname":"TAIZHOU","areaen":"taizhou","simpleen":"tz","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"123"},{"id":"7","areaname":"天津","sajm":"TSN","country":"中国","cjm":"CN","sijm":"ZBTJ","airport":"天津滨海机场","enname":"TIANJIN","areaen":"tianjin","simpleen":"tj","jianpin":"T","ishot":"1","status":"1","remark":"","row_number":"124"},{"id":"134","areaname":"通化","sajm":"TNH","country":"中国","cjm":"CN","sijm":"ZYTN","airport":"通化机场","enname":"TONGHUA","areaen":"tonghua","simpleen":"th","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"125"},{"id":"135","areaname":"通辽","sajm":"TGO","country":"中国","cjm":"CN","sijm":"ZBTL","airport":"通辽机场","enname":"TONGLIAO","areaen":"tongliao","simpleen":"tl","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"126"},{"id":"136","areaname":"铜仁","sajm":"TEN","country":"中国","cjm":"CN","sijm":"ZUTR","airport":"铜仁凤凰机场","enname":"TONGREN","areaen":"tongren","simpleen":"tr","jianpin":"T","ishot":"","status":"1","remark":"","row_number":"127"}],"W":[{"id":"137","areaname":"万州","sajm":"WXN","country":"中国","cjm":"CN","sijm":"ZUWX","airport":"重庆万州五桥机场","enname":"CHONGQING","areaen":"wanzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"128"},{"id":"138","areaname":"潍坊","sajm":"WEF","country":"中国","cjm":"CN","sijm":"ZSWF","airport":"潍坊机场","enname":"WEIFANG","areaen":"weifang","simpleen":"wf","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"129"},{"id":"144","areaname":"威海","sajm":"WEH","country":"中国","cjm":"CN","sijm":"ZSWH","airport":"威海文登大水泊机场","enname":"WEIHAI","areaen":"weihai","simpleen":"wh","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"130"},{"id":"75","areaname":"文山","sajm":"WNH","country":"中国","cjm":"CN","sijm":"ZPWS","airport":"云南文山普者黑机场","enname":"WENSHAN","areaen":"wenshan","simpleen":"ws","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"131"},{"id":"29","areaname":"温州","sajm":"WNZ","country":"中国","cjm":"CN","sijm":"ZSWZ","airport":"温州永强机场","enname":"WENZHOU","areaen":"wenzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"132"},{"id":"16","areaname":"武汉","sajm":"WUH","country":"中国","cjm":"CN","sijm":"ZHHH","airport":"武汉天河机场","enname":"WUHAN","areaen":"wuhan","simpleen":"wh","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"133"},{"id":"133","areaname":"乌兰浩特","sajm":"HLH","country":"中国","cjm":"CN","sijm":"ZBUL","airport":"乌兰浩特机场","enname":"ULANHOT","areaen":"wulanhaote","simpleen":"wlht","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"134"},{"id":"11","areaname":"乌鲁木齐","sajm":"URC","country":"中国","cjm":"CN","sijm":"ZWWW","airport":"乌鲁木齐地窝堡机场","enname":"URUMQI","areaen":"wulumuqi","simpleen":"wlmq","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"135"},{"id":"56","areaname":"无锡","sajm":"WUX","country":"中国","cjm":"CN","sijm":"ZSWX","airport":"无锡硕放机场","enname":"WUXI","areaen":"wuxi","simpleen":"wx","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"136"},{"id":"132","areaname":"武夷山","sajm":"WUS","country":"中国","cjm":"CN","sijm":"ZSWY","airport":"武夷山机场","enname":"WUYISHAN","areaen":"wuyishan","simpleen":"wys","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"137"},{"id":"130","areaname":"梧州","sajm":"WUZ","country":"中国","cjm":"CN","sijm":"ZGWZ","airport":"广西梧州长洲岛机场","enname":"WUZHOU","areaen":"wuzhou","simpleen":"wz","jianpin":"W","ishot":"","status":"1","remark":"","row_number":"138"}],"X":[{"id":"61","areaname":"厦门","sajm":"XMN","country":"中国","cjm":"CN","sijm":"ZSAM","airport":"厦门高崎机场","enname":"XIAMEN","areaen":"xiamen","simpleen":"xm","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"139"},{"id":"14","areaname":"西安","sajm":"XIY","country":"中国","cjm":"CN","sijm":"ZLXY","airport":"西安咸阳机场","enname":"XIAN","areaen":"xian","simpleen":"xa","jianpin":"X","ishot":"1","status":"1","remark":"","row_number":"140"},{"id":"36","areaname":"襄阳","sajm":"XFN","country":"中国","cjm":"CN","sijm":"ZHXF","airport":"襄阳刘集机场","enname":"XIANGYANG","areaen":"xiangyang","simpleen":"xy","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"141"},{"id":"114","areaname":"西昌","sajm":"XIC","country":"中国","cjm":"CN","sijm":"ZUXC","airport":"西昌青山机场","enname":"XICHANG","areaen":"xichang","simpleen":"xc","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"142"},{"id":"60","areaname":"锡林浩特","sajm":"XIL","country":"中国","cjm":"CN","sijm":"ZBXH","airport":"锡林浩特机场","enname":"XILINHOT","areaen":"xilinhaote","simpleen":"xlht","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"143"},{"id":"94","areaname":"兴城","sajm":"XEN","country":"中国","cjm":"CN","sijm":"ZYXC","airport":"兴城机场","enname":"HULUDAO","areaen":"xingcheng","simpleen":"xc","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"144"},{"id":"124","areaname":"西宁","sajm":"XNN","country":"中国","cjm":"CN","sijm":"ZLXN","airport":"西宁曹家堡机场","enname":"XINING","areaen":"xining","simpleen":"xn","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"145"},{"id":"21","areaname":"西双版纳","sajm":"JHG","country":"中国","cjm":"CN","sijm":"ZPJH","airport":"景洪(西双版纳)机场","enname":"JINGHONG","areaen":"xishuangbanna","simpleen":"xsbn","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"146"},{"id":"59","areaname":"徐州","sajm":"XUZ","country":"中国","cjm":"CN","sijm":"ZSXZ","airport":"徐州观音机场","enname":"XUZHOU","areaen":"xuzhou","simpleen":"xz","jianpin":"X","ishot":"","status":"1","remark":"","row_number":"147"}],"Y":[{"id":"122","areaname":"延安","sajm":"ENY","country":"中国","cjm":"CN","sijm":"ZLYA","airport":"延安二十里铺机场","enname":"YANAN","areaen":"yanan","simpleen":"ya","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"148"},{"id":"71","areaname":"盐城","sajm":"YNZ","country":"中国","cjm":"CN","sijm":"ZSYN","airport":"江苏盐城南洋机场","enname":"YANCHENG","areaen":"yancheng","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"149"},{"id":"119","areaname":"延吉","sajm":"YNJ","country":"中国","cjm":"CN","sijm":"ZYYJ","airport":"延吉朝阳川机场","enname":"YANJI","areaen":"yanji","simpleen":"yj","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"150"},{"id":"54","areaname":"烟台","sajm":"YNT","country":"中国","cjm":"CN","sijm":"ZSYT","airport":"烟台莱山机场","enname":"YANTAI","areaen":"yantai","simpleen":"yt","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"151"},{"id":"58","areaname":"宜宾","sajm":"YBP","country":"中国","cjm":"CN","sijm":"ZUYB","airport":"宜宾(菜坝)机场","enname":"YIBIN","areaen":"yibin","simpleen":"yb","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"152"},{"id":"127","areaname":"宜昌","sajm":"YIH","country":"中国","cjm":"CN","sijm":"ZHYC","airport":"宜昌三峡机场","enname":"YICHANG","areaen":"yichang","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"153"},{"id":"39","areaname":"银川","sajm":"INC","country":"中国","cjm":"CN","sijm":"ZLIC","airport":"银川河东机场","enname":"YINCHUAN","areaen":"yinchuan","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"154"},{"id":"159","areaname":"伊宁","sajm":"YIN","country":"中国","cjm":"CN","sijm":"ZWYN","airport":"伊宁机场","enname":"YINING","areaen":"yining","simpleen":"yn","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"155"},{"id":"57","areaname":"义乌","sajm":"YIW","country":"中国","cjm":"CN","sijm":"ZSYW","airport":"义乌机场","enname":"YIWU","areaen":"yiwu","simpleen":"yw","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"156"},{"id":"155","areaname":"榆林","sajm":"UYN","country":"中国","cjm":"CN","sijm":"ZLYL","airport":"榆林西沙机场","enname":"YULIN","areaen":"yulin","simpleen":"yl","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"157"},{"id":"167","areaname":"运城","sajm":"YCU","country":"中国","cjm":"CN","sijm":"ZBYC","airport":"运城关公机场","enname":"YUNCHENG","areaen":"yuncheng","simpleen":"yc","jianpin":"Y","ishot":"","status":"1","remark":"","row_number":"158"}],"Z":[{"id":"111","areaname":"张家界","sajm":"DYG","country":"中国","cjm":"CN","sijm":"ZGDY","airport":"张家界荷花机场","enname":"ZHANGJIAJIE","areaen":"zhangjiajie","simpleen":"zjj","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"159"},{"id":"31","areaname":"湛江","sajm":"ZHA","country":"中国","cjm":"CN","sijm":"ZGZJ","airport":"湛江机场","enname":"ZHANJIANG","areaen":"zhanjiang","simpleen":"zj","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"160"},{"id":"140","areaname":"昭通","sajm":"ZAT","country":"中国","cjm":"CN","sijm":"ZPZT","airport":"昭通机场","enname":"ZHAOTONG","areaen":"zhaotong","simpleen":"zt","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"161"},{"id":"55","areaname":"郑州","sajm":"CGO","country":"中国","cjm":"CN","sijm":"ZHCC","airport":"郑州新郑机场","enname":"ZHENGZHOU","areaen":"zhengzhou","simpleen":"zz","jianpin":"Z","ishot":"1","status":"1","remark":"","row_number":"162"},{"id":"146","areaname":"舟山","sajm":"HSN","country":"中国","cjm":"CN","sijm":"ZSZS","airport":"舟山普陀山朱家尖机场","enname":"ZHOUSHAN","areaen":"zhoushan","simpleen":"zs","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"163"},{"id":"44","areaname":"珠海","sajm":"ZUH","country":"中国","cjm":"CN","sijm":"ZGSD","airport":"珠海三灶机场","enname":"ZHUHAI","areaen":"zhuhai","simpleen":"zh","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"164"},{"id":"42","areaname":"遵义","sajm":"ZYI","country":"中国","cjm":"CN","sijm":"ZUZY","airport":"遵义机场","enname":"ZUNYI","areaen":"zunyi","simpleen":"zy","jianpin":"Z","ishot":"","status":"1","remark":"","row_number":"165"}]}
         */

        private OtherBean other;
        private List<HotBean> hot;

        public OtherBean getOther() {
            return other;
        }

        public void setOther(OtherBean other) {
            this.other = other;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public static class OtherBean {
            private List<ABean> A;
            private List<BBean> B;
            private List<CBean> C;
            private List<DBean> D;
            private List<EBean> E;
            private List<FBean> F;
            private List<GBean> G;
            private List<HBean> H;
            private List<JBean> J;
            private List<KBean> K;
            private List<LBean> L;
            private List<MBean> M;
            private List<NBean> N;
            private List<PBean> P;
            private List<QBean> Q;
            private List<SBean> S;
            private List<TBean> T;
            private List<WBean> W;
            private List<XBean> X;
            private List<YBean> Y;
            private List<ZBean> Z;

            public List<ABean> getA() {
                return A;
            }

            public void setA(List<ABean> A) {
                this.A = A;
            }

            public List<BBean> getB() {
                return B;
            }

            public void setB(List<BBean> B) {
                this.B = B;
            }

            public List<CBean> getC() {
                return C;
            }

            public void setC(List<CBean> C) {
                this.C = C;
            }

            public List<DBean> getD() {
                return D;
            }

            public void setD(List<DBean> D) {
                this.D = D;
            }

            public List<EBean> getE() {
                return E;
            }

            public void setE(List<EBean> E) {
                this.E = E;
            }

            public List<FBean> getF() {
                return F;
            }

            public void setF(List<FBean> F) {
                this.F = F;
            }

            public List<GBean> getG() {
                return G;
            }

            public void setG(List<GBean> G) {
                this.G = G;
            }

            public List<HBean> getH() {
                return H;
            }

            public void setH(List<HBean> H) {
                this.H = H;
            }

            public List<JBean> getJ() {
                return J;
            }

            public void setJ(List<JBean> J) {
                this.J = J;
            }

            public List<KBean> getK() {
                return K;
            }

            public void setK(List<KBean> K) {
                this.K = K;
            }

            public List<LBean> getL() {
                return L;
            }

            public void setL(List<LBean> L) {
                this.L = L;
            }

            public List<MBean> getM() {
                return M;
            }

            public void setM(List<MBean> M) {
                this.M = M;
            }

            public List<NBean> getN() {
                return N;
            }

            public void setN(List<NBean> N) {
                this.N = N;
            }

            public List<PBean> getP() {
                return P;
            }

            public void setP(List<PBean> P) {
                this.P = P;
            }

            public List<QBean> getQ() {
                return Q;
            }

            public void setQ(List<QBean> Q) {
                this.Q = Q;
            }

            public List<SBean> getS() {
                return S;
            }

            public void setS(List<SBean> S) {
                this.S = S;
            }

            public List<TBean> getT() {
                return T;
            }

            public void setT(List<TBean> T) {
                this.T = T;
            }

            public List<WBean> getW() {
                return W;
            }

            public void setW(List<WBean> W) {
                this.W = W;
            }

            public List<XBean> getX() {
                return X;
            }

            public void setX(List<XBean> X) {
                this.X = X;
            }

            public List<YBean> getY() {
                return Y;
            }

            public void setY(List<YBean> Y) {
                this.Y = Y;
            }

            public List<ZBean> getZ() {
                return Z;
            }

            public void setZ(List<ZBean> Z) {
                this.Z = Z;
            }

            public static class ABean {
                /**
                 * id : 43
                 * areaname : 阿克苏
                 * sajm : AKU
                 * country : 中国
                 * cjm : CN
                 * sijm : ZWAK
                 * airport : 阿克苏机场
                 * enname : AKSU
                 * areaen : aksu
                 * simpleen : aks
                 * jianpin : A
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 1
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class BBean {
                /**
                 * id : 98
                 * areaname : 保山
                 * sajm : BSD
                 * country : 中国
                 * cjm : CN
                 * sijm : ZPBS
                 * airport : 保山云瑞机场
                 * enname : BAOSHAN
                 * areaen : baoshan
                 * simpleen : bs
                 * jianpin : B
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 7
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class CBean {
                /**
                 * id : 26
                 * areaname : 长春
                 * sajm : CGQ
                 * country : 中国
                 * cjm : CN
                 * sijm : ZYCC
                 * airport : 长春龙嘉国际机场
                 * enname : CHANGCHUN
                 * areaen : changchun
                 * simpleen : cc
                 * jianpin : C
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 13
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class DBean {
                /**
                 * id : 48
                 * areaname : 大理
                 * sajm : DLU
                 * country : 中国
                 * cjm : CN
                 * sijm : ZPDL
                 * airport : 大理荒草坝机场
                 * enname : DALIXIAGUAN
                 * areaen : dali
                 * simpleen : dl
                 * jianpin : D
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 25
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class EBean {
                /**
                 * id : 37
                 * areaname : 鄂尔多斯
                 * sajm : DSN
                 * country : 中国
                 * cjm : CN
                 * sijm : ZBDS
                 * airport : 鄂尔多斯伊金霍洛机场
                 * enname : ORDOS
                 * areaen : eerduosi
                 * simpleen : eeds
                 * jianpin : E
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 33
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class FBean {
                /**
                 * id : 113
                 * areaname : 阜阳
                 * sajm : FUG
                 * country : 中国
                 * cjm : CN
                 * sijm : ZSFY
                 * airport : 阜阳西关机场
                 * enname : FUYANG
                 * areaen : fuyang
                 * simpleen : fy
                 * jianpin : F
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 35
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class GBean {
                /**
                 * id : 67
                 * areaname : 赣州
                 * sajm : KOW
                 * country : 中国
                 * cjm : CN
                 * sijm : ZSGZ
                 * airport : 赣州黄金机场
                 * enname : GANZHOU
                 * areaen : ganzhou
                 * simpleen : gz
                 * jianpin : G
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 38
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class HBean {
                /**
                 * id : 18
                 * areaname : 哈尔滨
                 * sajm : HRB
                 * country : 中国
                 * cjm : CN
                 * sijm : ZYHB
                 * airport : 哈尔滨太平机场
                 * enname : HARBIN
                 * areaen : haerbin
                 * simpleen : hrb
                 * jianpin : H
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 45
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class JBean {
                /**
                 * id : 87
                 * areaname : 佳木斯
                 * sajm : JMU
                 * country : 中国
                 * cjm : CN
                 * sijm : ZYJM
                 * airport : 佳木斯机场
                 * enname : JIAMUSI
                 * areaen : jiamusi
                 * simpleen : jms
                 * jianpin : J
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 59
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class KBean {
                /**
                 * id : 150
                 * areaname : 喀什
                 * sajm : KHG
                 * country : 中国
                 * cjm : CN
                 * sijm : ZWSH
                 * airport : 喀什机场
                 * enname : KASHGAR
                 * areaen : kashi
                 * simpleen : ks
                 * jianpin : K
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 73
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class LBean {
                /**
                 * id : 22
                 * areaname : 兰州
                 * sajm : LHW
                 * country : 中国
                 * cjm : CN
                 * sijm : ZLLL
                 * airport : 兰州机场
                 * enname : LANZHOU
                 * areaen : lanzhou
                 * simpleen : lz
                 * jianpin : L
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 78
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class MBean {
                /**
                 * id : 161
                 * areaname : 芒市
                 * sajm : LUM
                 * country : 中国
                 * cjm : CN
                 * sijm : ZPMS
                 * airport : 芒市机场
                 * enname : MANGSHI
                 * areaen : mangshi
                 * simpleen : ms
                 * jianpin : M
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 88
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class NBean {
                /**
                 * id : 74
                 * areaname : 那拉提
                 * sajm : NLT
                 * country : 中国
                 * cjm : CN
                 * sijm : ZWNL
                 * airport : 那拉提机场
                 * enname : NALATI
                 * areaen : nalati
                 * simpleen : nlt
                 * jianpin : N
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 92
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class PBean {
                /**
                 * id : 6
                 * areaname : 浦东
                 * sajm : PVG
                 * country : 中国
                 * cjm : CN
                 * sijm : ZSPD
                 * airport : 上海浦东机场
                 * enname : PUDONG
                 * areaen : pudong
                 * simpleen : pd
                 * jianpin : P
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 101
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class QBean {
                /**
                 * id : 118
                 * areaname : 且末
                 * sajm : IQM
                 * country : 中国
                 * cjm : CN
                 * sijm : ZWCM
                 * airport : 且末机场
                 * enname : QIEMO
                 * areaen : qiemo
                 * simpleen : qm
                 * jianpin : Q
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 102
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class SBean {
                /**
                 * id : 33
                 * areaname : 三亚
                 * sajm : SYX
                 * country : 中国
                 * cjm : CN
                 * sijm : ZJSY
                 * airport : 三亚凤凰机场
                 * enname : SANYA
                 * areaen : sanya
                 * simpleen : sy
                 * jianpin : S
                 * ishot : 1
                 * status : 1
                 * remark :
                 * row_number : 108
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class TBean {
                /**
                 * id : 131
                 * areaname : 塔城
                 * sajm : TCG
                 * country : 中国
                 * cjm : CN
                 * sijm : ZWTC
                 * airport : 塔城机场
                 * enname : TACHENG
                 * areaen : tacheng
                 * simpleen : tc
                 * jianpin : T
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 117
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class WBean {
                /**
                 * id : 137
                 * areaname : 万州
                 * sajm : WXN
                 * country : 中国
                 * cjm : CN
                 * sijm : ZUWX
                 * airport : 重庆万州五桥机场
                 * enname : CHONGQING
                 * areaen : wanzhou
                 * simpleen : wz
                 * jianpin : W
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 128
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class XBean {
                /**
                 * id : 61
                 * areaname : 厦门
                 * sajm : XMN
                 * country : 中国
                 * cjm : CN
                 * sijm : ZSAM
                 * airport : 厦门高崎机场
                 * enname : XIAMEN
                 * areaen : xiamen
                 * simpleen : xm
                 * jianpin : X
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 139
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class YBean {
                /**
                 * id : 122
                 * areaname : 延安
                 * sajm : ENY
                 * country : 中国
                 * cjm : CN
                 * sijm : ZLYA
                 * airport : 延安二十里铺机场
                 * enname : YANAN
                 * areaen : yanan
                 * simpleen : ya
                 * jianpin : Y
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 148
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }

            public static class ZBean {
                /**
                 * id : 111
                 * areaname : 张家界
                 * sajm : DYG
                 * country : 中国
                 * cjm : CN
                 * sijm : ZGDY
                 * airport : 张家界荷花机场
                 * enname : ZHANGJIAJIE
                 * areaen : zhangjiajie
                 * simpleen : zjj
                 * jianpin : Z
                 * ishot :
                 * status : 1
                 * remark :
                 * row_number : 159
                 */

                private String id;
                private String areaname;
                private String sajm;
                private String country;
                private String cjm;
                private String sijm;
                private String airport;
                private String enname;
                private String areaen;
                private String simpleen;
                private String jianpin;
                private String ishot;
                private String status;
                private String remark;
                private String row_number;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAreaname() {
                    return areaname;
                }

                public void setAreaname(String areaname) {
                    this.areaname = areaname;
                }

                public String getSajm() {
                    return sajm;
                }

                public void setSajm(String sajm) {
                    this.sajm = sajm;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getCjm() {
                    return cjm;
                }

                public void setCjm(String cjm) {
                    this.cjm = cjm;
                }

                public String getSijm() {
                    return sijm;
                }

                public void setSijm(String sijm) {
                    this.sijm = sijm;
                }

                public String getAirport() {
                    return airport;
                }

                public void setAirport(String airport) {
                    this.airport = airport;
                }

                public String getEnname() {
                    return enname;
                }

                public void setEnname(String enname) {
                    this.enname = enname;
                }

                public String getAreaen() {
                    return areaen;
                }

                public void setAreaen(String areaen) {
                    this.areaen = areaen;
                }

                public String getSimpleen() {
                    return simpleen;
                }

                public void setSimpleen(String simpleen) {
                    this.simpleen = simpleen;
                }

                public String getJianpin() {
                    return jianpin;
                }

                public void setJianpin(String jianpin) {
                    this.jianpin = jianpin;
                }

                public String getIshot() {
                    return ishot;
                }

                public void setIshot(String ishot) {
                    this.ishot = ishot;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getRow_number() {
                    return row_number;
                }

                public void setRow_number(String row_number) {
                    this.row_number = row_number;
                }
            }
        }

        public static class HotBean {
            /**
             * id : 1
             * areaname : 上海
             * sajm : SHA
             * country : 中国
             * cjm : CN
             * sijm : ZSSS
             * airport : 上海虹桥机场
             * enname : SHANGHAI
             * areaen : shanghai
             * simpleen : sh
             * jianpin : S
             * ishot : 1
             * status : 1
             * remark :
             * row_number : 1
             */

            private String id;
            private String areaname;
            private String sajm;
            private String country;
            private String cjm;
            private String sijm;
            private String airport;
            private String enname;
            private String areaen;
            private String simpleen;
            private String jianpin;
            private String ishot;
            private String status;
            private String remark;
            private String row_number;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAreaname() {
                return areaname;
            }

            public void setAreaname(String areaname) {
                this.areaname = areaname;
            }

            public String getSajm() {
                return sajm;
            }

            public void setSajm(String sajm) {
                this.sajm = sajm;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getCjm() {
                return cjm;
            }

            public void setCjm(String cjm) {
                this.cjm = cjm;
            }

            public String getSijm() {
                return sijm;
            }

            public void setSijm(String sijm) {
                this.sijm = sijm;
            }

            public String getAirport() {
                return airport;
            }

            public void setAirport(String airport) {
                this.airport = airport;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getAreaen() {
                return areaen;
            }

            public void setAreaen(String areaen) {
                this.areaen = areaen;
            }

            public String getSimpleen() {
                return simpleen;
            }

            public void setSimpleen(String simpleen) {
                this.simpleen = simpleen;
            }

            public String getJianpin() {
                return jianpin;
            }

            public void setJianpin(String jianpin) {
                this.jianpin = jianpin;
            }

            public String getIshot() {
                return ishot;
            }

            public void setIshot(String ishot) {
                this.ishot = ishot;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getRow_number() {
                return row_number;
            }

            public void setRow_number(String row_number) {
                this.row_number = row_number;
            }
        }
    }
}
