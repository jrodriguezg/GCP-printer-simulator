package com.jmrodrigg.model.Utils;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class LocalizedString {

    public enum Locale {
        /**
         * <code>AF = 0;</code>
         */
        AF(0, 0),
        /**
         * <code>AM = 1;</code>
         */
        AM(1, 1),
        /**
         * <code>AR = 2;</code>
         */
        AR(2, 2),
        /**
         * <code>AR_XB = 3;</code>
         */
        AR_XB(3, 3),
        /**
         * <code>BG = 4;</code>
         */
        BG(4, 4),
        /**
         * <code>BN = 5;</code>
         */
        BN(5, 5),
        /**
         * <code>CA = 6;</code>
         */
        CA(6, 6),
        /**
         * <code>CS = 7;</code>
         */
        CS(7, 7),
        /**
         * <code>CY = 8;</code>
         */
        CY(8, 8),
        /**
         * <code>DA = 9;</code>
         */
        DA(9, 9),
        /**
         * <code>DE = 10;</code>
         */
        DE(10, 10),
        /**
         * <code>DE_AT = 11;</code>
         */
        DE_AT(11, 11),
        /**
         * <code>DE_CH = 12;</code>
         */
        DE_CH(12, 12),
        /**
         * <code>EL = 13;</code>
         */
        EL(13, 13),
        /**
         * <code>EN = 14;</code>
         */
        EN(14, 14),
        /**
         * <code>EN_GB = 15;</code>
         */
        EN_GB(15, 15),
        /**
         * <code>EN_IE = 16;</code>
         */
        EN_IE(16, 16),
        /**
         * <code>EN_IN = 17;</code>
         */
        EN_IN(17, 17),
        /**
         * <code>EN_SG = 18;</code>
         */
        EN_SG(18, 18),
        /**
         * <code>EN_XA = 19;</code>
         */
        EN_XA(19, 19),
        /**
         * <code>EN_XC = 20;</code>
         */
        EN_XC(20, 20),
        /**
         * <code>EN_ZA = 21;</code>
         */
        EN_ZA(21, 21),
        /**
         * <code>ES = 22;</code>
         */
        ES(22, 22),
        /**
         * <code>ES_419 = 23;</code>
         */
        ES_419(23, 23),
        /**
         * <code>ES_AR = 24;</code>
         */
        ES_AR(24, 24),
        /**
         * <code>ES_BO = 25;</code>
         */
        ES_BO(25, 25),
        /**
         * <code>ES_CL = 26;</code>
         */
        ES_CL(26, 26),
        /**
         * <code>ES_CO = 27;</code>
         */
        ES_CO(27, 27),
        /**
         * <code>ES_CR = 28;</code>
         */
        ES_CR(28, 28),
        /**
         * <code>ES_DO = 29;</code>
         */
        ES_DO(29, 29),
        /**
         * <code>ES_EC = 30;</code>
         */
        ES_EC(30, 30),
        /**
         * <code>ES_GT = 31;</code>
         */
        ES_GT(31, 31),
        /**
         * <code>ES_HN = 32;</code>
         */
        ES_HN(32, 32),
        /**
         * <code>ES_MX = 33;</code>
         */
        ES_MX(33, 33),
        /**
         * <code>ES_NI = 34;</code>
         */
        ES_NI(34, 34),
        /**
         * <code>ES_PA = 35;</code>
         */
        ES_PA(35, 35),
        /**
         * <code>ES_PE = 36;</code>
         */
        ES_PE(36, 36),
        /**
         * <code>ES_PR = 37;</code>
         */
        ES_PR(37, 37),
        /**
         * <code>ES_PY = 38;</code>
         */
        ES_PY(38, 38),
        /**
         * <code>ES_SV = 39;</code>
         */
        ES_SV(39, 39),
        /**
         * <code>ES_US = 40;</code>
         */
        ES_US(40, 40),
        /**
         * <code>ES_UY = 41;</code>
         */
        ES_UY(41, 41),
        /**
         * <code>ES_VE = 42;</code>
         */
        ES_VE(42, 42),
        /**
         * <code>ET = 43;</code>
         */
        ET(43, 43),
        /**
         * <code>EU = 44;</code>
         */
        EU(44, 44),
        /**
         * <code>FA = 45;</code>
         */
        FA(45, 45),
        /**
         * <code>FI = 46;</code>
         */
        FI(46, 46),
        /**
         * <code>FR = 47;</code>
         */
        FR(47, 47),
        /**
         * <code>FR_CA = 48;</code>
         */
        FR_CA(48, 48),
        /**
         * <code>FR_CH = 49;</code>
         */
        FR_CH(49, 49),
        /**
         * <code>GL = 50;</code>
         */
        GL(50, 50),
        /**
         * <code>GU = 51;</code>
         */
        GU(51, 51),
        /**
         * <code>HE = 52;</code>
         */
        HE(52, 52),
        /**
         * <code>HI = 53;</code>
         */
        HI(53, 53),
        /**
         * <code>HR = 54;</code>
         */
        HR(54, 54),
        /**
         * <code>HU = 55;</code>
         */
        HU(55, 55),
        /**
         * <code>HY = 56;</code>
         */
        HY(56, 56),
        /**
         * <code>ID = 57;</code>
         */
        ID(57, 57),
        /**
         * <code>IN = 58;</code>
         */
        IN(58, 58),
        /**
         * <code>IT = 59;</code>
         */
        IT(59, 59),
        /**
         * <code>JA = 60;</code>
         */
        JA(60, 60),
        /**
         * <code>KA = 61;</code>
         */
        KA(61, 61),
        /**
         * <code>KM = 62;</code>
         */
        KM(62, 62),
        /**
         * <code>KN = 63;</code>
         */
        KN(63, 63),
        /**
         * <code>KO = 64;</code>
         */
        KO(64, 64),
        /**
         * <code>LN = 65;</code>
         */
        LN(65, 65),
        /**
         * <code>LO = 66;</code>
         */
        LO(66, 66),
        /**
         * <code>LT = 67;</code>
         */
        LT(67, 67),
        /**
         * <code>LV = 68;</code>
         */
        LV(68, 68),
        /**
         * <code>ML = 69;</code>
         */
        ML(69, 69),
        /**
         * <code>MO = 70;</code>
         */
        MO(70, 70),
        /**
         * <code>MR = 71;</code>
         */
        MR(71, 71),
        /**
         * <code>MS = 72;</code>
         */
        MS(72, 72),
        /**
         * <code>NB = 73;</code>
         */
        NB(73, 73),
        /**
         * <code>NE = 74;</code>
         */
        NE(74, 74),
        /**
         * <code>NL = 75;</code>
         */
        NL(75, 75),
        /**
         * <code>NO = 76;</code>
         */
        NO(76, 76),
        /**
         * <code>PL = 77;</code>
         */
        PL(77, 77),
        /**
         * <code>PT = 78;</code>
         */
        PT(78, 78),
        /**
         * <code>PT_BR = 79;</code>
         */
        PT_BR(79, 79),
        /**
         * <code>PT_PT = 80;</code>
         */
        PT_PT(80, 80),
        /**
         * <code>RM = 81;</code>
         */
        RM(81, 81),
        /**
         * <code>RO = 82;</code>
         */
        RO(82, 82),
        /**
         * <code>RU = 83;</code>
         */
        RU(83, 83),
        /**
         * <code>SK = 84;</code>
         */
        SK(84, 84),
        /**
         * <code>SL = 85;</code>
         */
        SL(85, 85),
        /**
         * <code>SR = 86;</code>
         */
        SR(86, 86),
        /**
         * <code>SR_LATN = 87;</code>
         */
        SR_LATN(87, 87),
        /**
         * <code>SV = 88;</code>
         */
        SV(88, 88),
        /**
         * <code>SW = 89;</code>
         */
        SW(89, 89),
        /**
         * <code>TA = 90;</code>
         */
        TA(90, 90),
        /**
         * <code>TE = 91;</code>
         */
        TE(91, 91),
        /**
         * <code>TH = 92;</code>
         */
        TH(92, 92),
        /**
         * <code>TL = 93;</code>
         */
        TL(93, 93),
        /**
         * <code>TR = 94;</code>
         */
        TR(94, 94),
        /**
         * <code>UK = 95;</code>
         */
        UK(95, 95),
        /**
         * <code>UR = 96;</code>
         */
        UR(96, 96),
        /**
         * <code>VI = 97;</code>
         */
        VI(97, 97),
        /**
         * <code>ZH = 98;</code>
         */
        ZH(98, 98),
        /**
         * <code>ZH_CN = 99;</code>
         */
        ZH_CN(99, 99),
        /**
         * <code>ZH_HK = 100;</code>
         */
        ZH_HK(100, 100),
        /**
         * <code>ZH_TW = 101;</code>
         */
        ZH_TW(101, 101),
        /**
         * <code>ZU = 102;</code>
         */
        ZU(102, 102),
        ;

        /**
         * <code>AF = 0;</code>
         */
        public static final int AF_VALUE = 0;
        /**
         * <code>AM = 1;</code>
         */
        public static final int AM_VALUE = 1;
        /**
         * <code>AR = 2;</code>
         */
        public static final int AR_VALUE = 2;
        /**
         * <code>AR_XB = 3;</code>
         */
        public static final int AR_XB_VALUE = 3;
        /**
         * <code>BG = 4;</code>
         */
        public static final int BG_VALUE = 4;
        /**
         * <code>BN = 5;</code>
         */
        public static final int BN_VALUE = 5;
        /**
         * <code>CA = 6;</code>
         */
        public static final int CA_VALUE = 6;
        /**
         * <code>CS = 7;</code>
         */
        public static final int CS_VALUE = 7;
        /**
         * <code>CY = 8;</code>
         */
        public static final int CY_VALUE = 8;
        /**
         * <code>DA = 9;</code>
         */
        public static final int DA_VALUE = 9;
        /**
         * <code>DE = 10;</code>
         */
        public static final int DE_VALUE = 10;
        /**
         * <code>DE_AT = 11;</code>
         */
        public static final int DE_AT_VALUE = 11;
        /**
         * <code>DE_CH = 12;</code>
         */
        public static final int DE_CH_VALUE = 12;
        /**
         * <code>EL = 13;</code>
         */
        public static final int EL_VALUE = 13;
        /**
         * <code>EN = 14;</code>
         */
        public static final int EN_VALUE = 14;
        /**
         * <code>EN_GB = 15;</code>
         */
        public static final int EN_GB_VALUE = 15;
        /**
         * <code>EN_IE = 16;</code>
         */
        public static final int EN_IE_VALUE = 16;
        /**
         * <code>EN_IN = 17;</code>
         */
        public static final int EN_IN_VALUE = 17;
        /**
         * <code>EN_SG = 18;</code>
         */
        public static final int EN_SG_VALUE = 18;
        /**
         * <code>EN_XA = 19;</code>
         */
        public static final int EN_XA_VALUE = 19;
        /**
         * <code>EN_XC = 20;</code>
         */
        public static final int EN_XC_VALUE = 20;
        /**
         * <code>EN_ZA = 21;</code>
         */
        public static final int EN_ZA_VALUE = 21;
        /**
         * <code>ES = 22;</code>
         */
        public static final int ES_VALUE = 22;
        /**
         * <code>ES_419 = 23;</code>
         */
        public static final int ES_419_VALUE = 23;
        /**
         * <code>ES_AR = 24;</code>
         */
        public static final int ES_AR_VALUE = 24;
        /**
         * <code>ES_BO = 25;</code>
         */
        public static final int ES_BO_VALUE = 25;
        /**
         * <code>ES_CL = 26;</code>
         */
        public static final int ES_CL_VALUE = 26;
        /**
         * <code>ES_CO = 27;</code>
         */
        public static final int ES_CO_VALUE = 27;
        /**
         * <code>ES_CR = 28;</code>
         */
        public static final int ES_CR_VALUE = 28;
        /**
         * <code>ES_DO = 29;</code>
         */
        public static final int ES_DO_VALUE = 29;
        /**
         * <code>ES_EC = 30;</code>
         */
        public static final int ES_EC_VALUE = 30;
        /**
         * <code>ES_GT = 31;</code>
         */
        public static final int ES_GT_VALUE = 31;
        /**
         * <code>ES_HN = 32;</code>
         */
        public static final int ES_HN_VALUE = 32;
        /**
         * <code>ES_MX = 33;</code>
         */
        public static final int ES_MX_VALUE = 33;
        /**
         * <code>ES_NI = 34;</code>
         */
        public static final int ES_NI_VALUE = 34;
        /**
         * <code>ES_PA = 35;</code>
         */
        public static final int ES_PA_VALUE = 35;
        /**
         * <code>ES_PE = 36;</code>
         */
        public static final int ES_PE_VALUE = 36;
        /**
         * <code>ES_PR = 37;</code>
         */
        public static final int ES_PR_VALUE = 37;
        /**
         * <code>ES_PY = 38;</code>
         */
        public static final int ES_PY_VALUE = 38;
        /**
         * <code>ES_SV = 39;</code>
         */
        public static final int ES_SV_VALUE = 39;
        /**
         * <code>ES_US = 40;</code>
         */
        public static final int ES_US_VALUE = 40;
        /**
         * <code>ES_UY = 41;</code>
         */
        public static final int ES_UY_VALUE = 41;
        /**
         * <code>ES_VE = 42;</code>
         */
        public static final int ES_VE_VALUE = 42;
        /**
         * <code>ET = 43;</code>
         */
        public static final int ET_VALUE = 43;
        /**
         * <code>EU = 44;</code>
         */
        public static final int EU_VALUE = 44;
        /**
         * <code>FA = 45;</code>
         */
        public static final int FA_VALUE = 45;
        /**
         * <code>FI = 46;</code>
         */
        public static final int FI_VALUE = 46;
        /**
         * <code>FR = 47;</code>
         */
        public static final int FR_VALUE = 47;
        /**
         * <code>FR_CA = 48;</code>
         */
        public static final int FR_CA_VALUE = 48;
        /**
         * <code>FR_CH = 49;</code>
         */
        public static final int FR_CH_VALUE = 49;
        /**
         * <code>GL = 50;</code>
         */
        public static final int GL_VALUE = 50;
        /**
         * <code>GU = 51;</code>
         */
        public static final int GU_VALUE = 51;
        /**
         * <code>HE = 52;</code>
         */
        public static final int HE_VALUE = 52;
        /**
         * <code>HI = 53;</code>
         */
        public static final int HI_VALUE = 53;
        /**
         * <code>HR = 54;</code>
         */
        public static final int HR_VALUE = 54;
        /**
         * <code>HU = 55;</code>
         */
        public static final int HU_VALUE = 55;
        /**
         * <code>HY = 56;</code>
         */
        public static final int HY_VALUE = 56;
        /**
         * <code>ID = 57;</code>
         */
        public static final int ID_VALUE = 57;
        /**
         * <code>IN = 58;</code>
         */
        public static final int IN_VALUE = 58;
        /**
         * <code>IT = 59;</code>
         */
        public static final int IT_VALUE = 59;
        /**
         * <code>JA = 60;</code>
         */
        public static final int JA_VALUE = 60;
        /**
         * <code>KA = 61;</code>
         */
        public static final int KA_VALUE = 61;
        /**
         * <code>KM = 62;</code>
         */
        public static final int KM_VALUE = 62;
        /**
         * <code>KN = 63;</code>
         */
        public static final int KN_VALUE = 63;
        /**
         * <code>KO = 64;</code>
         */
        public static final int KO_VALUE = 64;
        /**
         * <code>LN = 65;</code>
         */
        public static final int LN_VALUE = 65;
        /**
         * <code>LO = 66;</code>
         */
        public static final int LO_VALUE = 66;
        /**
         * <code>LT = 67;</code>
         */
        public static final int LT_VALUE = 67;
        /**
         * <code>LV = 68;</code>
         */
        public static final int LV_VALUE = 68;
        /**
         * <code>ML = 69;</code>
         */
        public static final int ML_VALUE = 69;
        /**
         * <code>MO = 70;</code>
         */
        public static final int MO_VALUE = 70;
        /**
         * <code>MR = 71;</code>
         */
        public static final int MR_VALUE = 71;
        /**
         * <code>MS = 72;</code>
         */
        public static final int MS_VALUE = 72;
        /**
         * <code>NB = 73;</code>
         */
        public static final int NB_VALUE = 73;
        /**
         * <code>NE = 74;</code>
         */
        public static final int NE_VALUE = 74;
        /**
         * <code>NL = 75;</code>
         */
        public static final int NL_VALUE = 75;
        /**
         * <code>NO = 76;</code>
         */
        public static final int NO_VALUE = 76;
        /**
         * <code>PL = 77;</code>
         */
        public static final int PL_VALUE = 77;
        /**
         * <code>PT = 78;</code>
         */
        public static final int PT_VALUE = 78;
        /**
         * <code>PT_BR = 79;</code>
         */
        public static final int PT_BR_VALUE = 79;
        /**
         * <code>PT_PT = 80;</code>
         */
        public static final int PT_PT_VALUE = 80;
        /**
         * <code>RM = 81;</code>
         */
        public static final int RM_VALUE = 81;
        /**
         * <code>RO = 82;</code>
         */
        public static final int RO_VALUE = 82;
        /**
         * <code>RU = 83;</code>
         */
        public static final int RU_VALUE = 83;
        /**
         * <code>SK = 84;</code>
         */
        public static final int SK_VALUE = 84;
        /**
         * <code>SL = 85;</code>
         */
        public static final int SL_VALUE = 85;
        /**
         * <code>SR = 86;</code>
         */
        public static final int SR_VALUE = 86;
        /**
         * <code>SR_LATN = 87;</code>
         */
        public static final int SR_LATN_VALUE = 87;
        /**
         * <code>SV = 88;</code>
         */
        public static final int SV_VALUE = 88;
        /**
         * <code>SW = 89;</code>
         */
        public static final int SW_VALUE = 89;
        /**
         * <code>TA = 90;</code>
         */
        public static final int TA_VALUE = 90;
        /**
         * <code>TE = 91;</code>
         */
        public static final int TE_VALUE = 91;
        /**
         * <code>TH = 92;</code>
         */
        public static final int TH_VALUE = 92;
        /**
         * <code>TL = 93;</code>
         */
        public static final int TL_VALUE = 93;
        /**
         * <code>TR = 94;</code>
         */
        public static final int TR_VALUE = 94;
        /**
         * <code>UK = 95;</code>
         */
        public static final int UK_VALUE = 95;
        /**
         * <code>UR = 96;</code>
         */
        public static final int UR_VALUE = 96;
        /**
         * <code>VI = 97;</code>
         */
        public static final int VI_VALUE = 97;
        /**
         * <code>ZH = 98;</code>
         */
        public static final int ZH_VALUE = 98;
        /**
         * <code>ZH_CN = 99;</code>
         */
        public static final int ZH_CN_VALUE = 99;
        /**
         * <code>ZH_HK = 100;</code>
         */
        public static final int ZH_HK_VALUE = 100;
        /**
         * <code>ZH_TW = 101;</code>
         */
        public static final int ZH_TW_VALUE = 101;
        /**
         * <code>ZU = 102;</code>
         */
        public static final int ZU_VALUE = 102;


        public final int getNumber() { return value; }

        public static Locale valueOf(int value) {
            switch (value) {
                case 0: return AF;
                case 1: return AM;
                case 2: return AR;
                case 3: return AR_XB;
                case 4: return BG;
                case 5: return BN;
                case 6: return CA;
                case 7: return CS;
                case 8: return CY;
                case 9: return DA;
                case 10: return DE;
                case 11: return DE_AT;
                case 12: return DE_CH;
                case 13: return EL;
                case 14: return EN;
                case 15: return EN_GB;
                case 16: return EN_IE;
                case 17: return EN_IN;
                case 18: return EN_SG;
                case 19: return EN_XA;
                case 20: return EN_XC;
                case 21: return EN_ZA;
                case 22: return ES;
                case 23: return ES_419;
                case 24: return ES_AR;
                case 25: return ES_BO;
                case 26: return ES_CL;
                case 27: return ES_CO;
                case 28: return ES_CR;
                case 29: return ES_DO;
                case 30: return ES_EC;
                case 31: return ES_GT;
                case 32: return ES_HN;
                case 33: return ES_MX;
                case 34: return ES_NI;
                case 35: return ES_PA;
                case 36: return ES_PE;
                case 37: return ES_PR;
                case 38: return ES_PY;
                case 39: return ES_SV;
                case 40: return ES_US;
                case 41: return ES_UY;
                case 42: return ES_VE;
                case 43: return ET;
                case 44: return EU;
                case 45: return FA;
                case 46: return FI;
                case 47: return FR;
                case 48: return FR_CA;
                case 49: return FR_CH;
                case 50: return GL;
                case 51: return GU;
                case 52: return HE;
                case 53: return HI;
                case 54: return HR;
                case 55: return HU;
                case 56: return HY;
                case 57: return ID;
                case 58: return IN;
                case 59: return IT;
                case 60: return JA;
                case 61: return KA;
                case 62: return KM;
                case 63: return KN;
                case 64: return KO;
                case 65: return LN;
                case 66: return LO;
                case 67: return LT;
                case 68: return LV;
                case 69: return ML;
                case 70: return MO;
                case 71: return MR;
                case 72: return MS;
                case 73: return NB;
                case 74: return NE;
                case 75: return NL;
                case 76: return NO;
                case 77: return PL;
                case 78: return PT;
                case 79: return PT_BR;
                case 80: return PT_PT;
                case 81: return RM;
                case 82: return RO;
                case 83: return RU;
                case 84: return SK;
                case 85: return SL;
                case 86: return SR;
                case 87: return SR_LATN;
                case 88: return SV;
                case 89: return SW;
                case 90: return TA;
                case 91: return TE;
                case 92: return TH;
                case 93: return TL;
                case 94: return TR;
                case 95: return UK;
                case 96: return UR;
                case 97: return VI;
                case 98: return ZH;
                case 99: return ZH_CN;
                case 100: return ZH_HK;
                case 101: return ZH_TW;
                case 102: return ZU;
                default: return null;
            }
        }

        private static final Locale[] VALUES = values();

        private final int index;
        private final int value;

        private Locale(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    Locale locale;
    String value;
}
