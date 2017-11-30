package cn.houno.houniaolvju.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-10-17.
 */

public class FlightModifyRefundBean {

    /**
     * status : 0
     * data : {"externRuleInfo":"W3sicGFzc2VuZ2VyVHlwZSI6MiwicnVsZUZsYWciOjEsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MiwicnVsZUZsYWciOjIsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MSwicnVsZUZsYWciOjEsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MSwicnVsZUZsYWciOjIsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19XQ==","noticeMsg":"","infRuleDesc":"改期退票免收手续费，具体以实际订单为准","bakRule":"","ruleInfos":[{"ruleFlag":1,"ruleRemark":"不得退票。","passengerType":1,"ruleFeeList":[{"name":"起飞前","value":"240元/人"},{"name":"起飞后","value":"240元/人"}],"calculateType":2,"ruleName":"退票手续费","basePrice":840},{"ruleFlag":2,"ruleRemark":"不得改期。","passengerType":1,"ruleFeeList":[{"name":"起飞前","value":"240元/人"},{"name":"起飞后","value":"240元/人"}],"calculateType":2,"ruleName":"同舱改期手续费","basePrice":840}]}
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
         * externRuleInfo : W3sicGFzc2VuZ2VyVHlwZSI6MiwicnVsZUZsYWciOjEsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MiwicnVsZUZsYWciOjIsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MSwicnVsZUZsYWciOjEsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19LHsicGFzc2VuZ2VyVHlwZSI6MSwicnVsZUZsYWciOjIsInJ1bGVJdGVtcyI6W3siYWxsb3ciOmZhbHNlLCJhbW91bnQiOjI0MCwicmF0ZSI6MX0seyJhbGxvdyI6ZmFsc2UsImFtb3VudCI6MjQwLCJyYXRlIjoxfV19XQ==
         * noticeMsg :
         * infRuleDesc : 改期退票免收手续费，具体以实际订单为准
         * bakRule :
         * ruleInfos : [{"ruleFlag":1,"ruleRemark":"不得退票。","passengerType":1,"ruleFeeList":[{"name":"起飞前","value":"240元/人"},{"name":"起飞后","value":"240元/人"}],"calculateType":2,"ruleName":"退票手续费","basePrice":840},{"ruleFlag":2,"ruleRemark":"不得改期。","passengerType":1,"ruleFeeList":[{"name":"起飞前","value":"240元/人"},{"name":"起飞后","value":"240元/人"}],"calculateType":2,"ruleName":"同舱改期手续费","basePrice":840}]
         */

        private String externRuleInfo;
        private String noticeMsg;
        private String infRuleDesc;
        private String bakRule;
        private List<RuleInfosBean> ruleInfos;

        public String getExternRuleInfo() {
            return externRuleInfo;
        }

        public void setExternRuleInfo(String externRuleInfo) {
            this.externRuleInfo = externRuleInfo;
        }

        public String getNoticeMsg() {
            return noticeMsg;
        }

        public void setNoticeMsg(String noticeMsg) {
            this.noticeMsg = noticeMsg;
        }

        public String getInfRuleDesc() {
            return infRuleDesc;
        }

        public void setInfRuleDesc(String infRuleDesc) {
            this.infRuleDesc = infRuleDesc;
        }

        public String getBakRule() {
            return bakRule;
        }

        public void setBakRule(String bakRule) {
            this.bakRule = bakRule;
        }

        public List<RuleInfosBean> getRuleInfos() {
            return ruleInfos;
        }

        public void setRuleInfos(List<RuleInfosBean> ruleInfos) {
            this.ruleInfos = ruleInfos;
        }

        public static class RuleInfosBean {
            /**
             * ruleFlag : 1
             * ruleRemark : 不得退票。
             * passengerType : 1
             * ruleFeeList : [{"name":"起飞前","value":"240元/人"},{"name":"起飞后","value":"240元/人"}]
             * calculateType : 2
             * ruleName : 退票手续费
             * basePrice : 840
             */

            private int ruleFlag;
            private String ruleRemark;
            private int passengerType;
            private int calculateType;
            private String ruleName;
            private int basePrice;
            private List<RuleFeeListBean> ruleFeeList;

            public int getRuleFlag() {
                return ruleFlag;
            }

            public void setRuleFlag(int ruleFlag) {
                this.ruleFlag = ruleFlag;
            }

            public String getRuleRemark() {
                return ruleRemark;
            }

            public void setRuleRemark(String ruleRemark) {
                this.ruleRemark = ruleRemark;
            }

            public int getPassengerType() {
                return passengerType;
            }

            public void setPassengerType(int passengerType) {
                this.passengerType = passengerType;
            }

            public int getCalculateType() {
                return calculateType;
            }

            public void setCalculateType(int calculateType) {
                this.calculateType = calculateType;
            }

            public String getRuleName() {
                return ruleName;
            }

            public void setRuleName(String ruleName) {
                this.ruleName = ruleName;
            }

            public int getBasePrice() {
                return basePrice;
            }

            public void setBasePrice(int basePrice) {
                this.basePrice = basePrice;
            }

            public List<RuleFeeListBean> getRuleFeeList() {
                return ruleFeeList;
            }

            public void setRuleFeeList(List<RuleFeeListBean> ruleFeeList) {
                this.ruleFeeList = ruleFeeList;
            }

            public static class RuleFeeListBean {
                /**
                 * name : 起飞前
                 * value : 240元/人
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
