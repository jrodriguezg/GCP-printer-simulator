package com.jmrodrigg.model.CDD;

import com.jmrodrigg.model.Utils.LocalizedString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguezg on 7/29/16.
 */
public class MediaSize {

    public enum Name {
        /**
         * <code>CUSTOM = 0;</code>
         */
        CUSTOM(0, 0),
        /**
         * <code>NA_INDEX_3X5 = 100;</code>
         *
         * <pre>
         * North American standard sheet media names.
         * </pre>
         */
        NA_INDEX_3X5(1, 100),
        /**
         * <code>NA_PERSONAL = 101;</code>
         */
        NA_PERSONAL(2, 101),
        /**
         * <code>NA_MONARCH = 102;</code>
         */
        NA_MONARCH(3, 102),
        /**
         * <code>NA_NUMBER_9 = 103;</code>
         */
        NA_NUMBER_9(4, 103),
        /**
         * <code>NA_INDEX_4X6 = 104;</code>
         */
        NA_INDEX_4X6(5, 104),
        /**
         * <code>NA_NUMBER_10 = 105;</code>
         */
        NA_NUMBER_10(6, 105),
        /**
         * <code>NA_A2 = 106;</code>
         */
        NA_A2(7, 106),
        /**
         * <code>NA_NUMBER_11 = 107;</code>
         */
        NA_NUMBER_11(8, 107),
        /**
         * <code>NA_NUMBER_12 = 108;</code>
         */
        NA_NUMBER_12(9, 108),
        /**
         * <code>NA_5X7 = 109;</code>
         */
        NA_5X7(10, 109),
        /**
         * <code>NA_INDEX_5X8 = 110;</code>
         */
        NA_INDEX_5X8(11, 110),
        /**
         * <code>NA_NUMBER_14 = 111;</code>
         */
        NA_NUMBER_14(12, 111),
        /**
         * <code>NA_INVOICE = 112;</code>
         */
        NA_INVOICE(13, 112),
        /**
         * <code>NA_INDEX_4X6_EXT = 113;</code>
         */
        NA_INDEX_4X6_EXT(14, 113),
        /**
         * <code>NA_6X9 = 114;</code>
         */
        NA_6X9(15, 114),
        /**
         * <code>NA_C5 = 115;</code>
         */
        NA_C5(16, 115),
        /**
         * <code>NA_7X9 = 116;</code>
         */
        NA_7X9(17, 116),
        /**
         * <code>NA_EXECUTIVE = 117;</code>
         */
        NA_EXECUTIVE(18, 117),
        /**
         * <code>NA_GOVT_LETTER = 118;</code>
         */
        NA_GOVT_LETTER(19, 118),
        /**
         * <code>NA_GOVT_LEGAL = 119;</code>
         */
        NA_GOVT_LEGAL(20, 119),
        /**
         * <code>NA_QUARTO = 120;</code>
         */
        NA_QUARTO(21, 120),
        /**
         * <code>NA_LETTER = 121;</code>
         */
        NA_LETTER(22, 121),
        /**
         * <code>NA_FANFOLD_EUR = 122;</code>
         */
        NA_FANFOLD_EUR(23, 122),
        /**
         * <code>NA_LETTER_PLUS = 123;</code>
         */
        NA_LETTER_PLUS(24, 123),
        /**
         * <code>NA_FOOLSCAP = 124;</code>
         */
        NA_FOOLSCAP(25, 124),
        /**
         * <code>NA_LEGAL = 125;</code>
         */
        NA_LEGAL(26, 125),
        /**
         * <code>NA_SUPER_A = 126;</code>
         */
        NA_SUPER_A(27, 126),
        /**
         * <code>NA_9X11 = 127;</code>
         */
        NA_9X11(28, 127),
        /**
         * <code>NA_ARCH_A = 128;</code>
         */
        NA_ARCH_A(29, 128),
        /**
         * <code>NA_LETTER_EXTRA = 129;</code>
         */
        NA_LETTER_EXTRA(30, 129),
        /**
         * <code>NA_LEGAL_EXTRA = 130;</code>
         */
        NA_LEGAL_EXTRA(31, 130),
        /**
         * <code>NA_10X11 = 131;</code>
         */
        NA_10X11(32, 131),
        /**
         * <code>NA_10X13 = 132;</code>
         */
        NA_10X13(33, 132),
        /**
         * <code>NA_10X14 = 133;</code>
         */
        NA_10X14(34, 133),
        /**
         * <code>NA_10X15 = 134;</code>
         */
        NA_10X15(35, 134),
        /**
         * <code>NA_11X12 = 135;</code>
         */
        NA_11X12(36, 135),
        /**
         * <code>NA_EDP = 136;</code>
         */
        NA_EDP(37, 136),
        /**
         * <code>NA_FANFOLD_US = 137;</code>
         */
        NA_FANFOLD_US(38, 137),
        /**
         * <code>NA_11X15 = 138;</code>
         */
        NA_11X15(39, 138),
        /**
         * <code>NA_LEDGER = 139;</code>
         */
        NA_LEDGER(40, 139),
        /**
         * <code>NA_EUR_EDP = 140;</code>
         */
        NA_EUR_EDP(41, 140),
        /**
         * <code>NA_ARCH_B = 141;</code>
         */
        NA_ARCH_B(42, 141),
        /**
         * <code>NA_12X19 = 142;</code>
         */
        NA_12X19(43, 142),
        /**
         * <code>NA_B_PLUS = 143;</code>
         */
        NA_B_PLUS(44, 143),
        /**
         * <code>NA_SUPER_B = 144;</code>
         */
        NA_SUPER_B(45, 144),
        /**
         * <code>NA_C = 145;</code>
         */
        NA_C(46, 145),
        /**
         * <code>NA_ARCH_C = 146;</code>
         */
        NA_ARCH_C(47, 146),
        /**
         * <code>NA_D = 147;</code>
         */
        NA_D(48, 147),
        /**
         * <code>NA_ARCH_D = 148;</code>
         */
        NA_ARCH_D(49, 148),
        /**
         * <code>NA_ASME_F = 149;</code>
         */
        NA_ASME_F(50, 149),
        /**
         * <code>NA_WIDE_FORMAT = 150;</code>
         */
        NA_WIDE_FORMAT(51, 150),
        /**
         * <code>NA_E = 151;</code>
         */
        NA_E(52, 151),
        /**
         * <code>NA_ARCH_E = 152;</code>
         */
        NA_ARCH_E(53, 152),
        /**
         * <code>NA_F = 153;</code>
         */
        NA_F(54, 153),
        /**
         * <code>ROC_16K = 200;</code>
         *
         * <pre>
         * Chinese standard sheet media size names.
         * </pre>
         */
        ROC_16K(55, 200),
        /**
         * <code>ROC_8K = 201;</code>
         */
        ROC_8K(56, 201),
        /**
         * <code>PRC_32K = 202;</code>
         */
        PRC_32K(57, 202),
        /**
         * <code>PRC_1 = 203;</code>
         */
        PRC_1(58, 203),
        /**
         * <code>PRC_2 = 204;</code>
         */
        PRC_2(59, 204),
        /**
         * <code>PRC_4 = 205;</code>
         */
        PRC_4(60, 205),
        /**
         * <code>PRC_5 = 206;</code>
         */
        PRC_5(61, 206),
        /**
         * <code>PRC_8 = 207;</code>
         */
        PRC_8(62, 207),
        /**
         * <code>PRC_6 = 208;</code>
         */
        PRC_6(63, 208),
        /**
         * <code>PRC_3 = 209;</code>
         */
        PRC_3(64, 209),
        /**
         * <code>PRC_16K = 210;</code>
         */
        PRC_16K(65, 210),
        /**
         * <code>PRC_7 = 211;</code>
         */
        PRC_7(66, 211),
        /**
         * <code>OM_JUURO_KU_KAI = 212;</code>
         */
        OM_JUURO_KU_KAI(67, 212),
        /**
         * <code>OM_PA_KAI = 213;</code>
         */
        OM_PA_KAI(68, 213),
        /**
         * <code>OM_DAI_PA_KAI = 214;</code>
         */
        OM_DAI_PA_KAI(69, 214),
        /**
         * <code>PRC_10 = 215;</code>
         */
        PRC_10(70, 215),
        /**
         * <code>ISO_A10 = 301;</code>
         *
         * <pre>
         * ISO standard sheet media size names.
         * </pre>
         */
        ISO_A10(71, 301),
        /**
         * <code>ISO_A9 = 302;</code>
         */
        ISO_A9(72, 302),
        /**
         * <code>ISO_A8 = 303;</code>
         */
        ISO_A8(73, 303),
        /**
         * <code>ISO_A7 = 304;</code>
         */
        ISO_A7(74, 304),
        /**
         * <code>ISO_A6 = 305;</code>
         */
        ISO_A6(75, 305),
        /**
         * <code>ISO_A5 = 306;</code>
         */
        ISO_A5(76, 306),
        /**
         * <code>ISO_A5_EXTRA = 307;</code>
         */
        ISO_A5_EXTRA(77, 307),
        /**
         * <code>ISO_A4 = 308;</code>
         */
        ISO_A4(78, 308),
        /**
         * <code>ISO_A4_TAB = 309;</code>
         */
        ISO_A4_TAB(79, 309),
        /**
         * <code>ISO_A4_EXTRA = 310;</code>
         */
        ISO_A4_EXTRA(80, 310),
        /**
         * <code>ISO_A3 = 311;</code>
         */
        ISO_A3(81, 311),
        /**
         * <code>ISO_A4X3 = 312;</code>
         */
        ISO_A4X3(82, 312),
        /**
         * <code>ISO_A4X4 = 313;</code>
         */
        ISO_A4X4(83, 313),
        /**
         * <code>ISO_A4X5 = 314;</code>
         */
        ISO_A4X5(84, 314),
        /**
         * <code>ISO_A4X6 = 315;</code>
         */
        ISO_A4X6(85, 315),
        /**
         * <code>ISO_A4X7 = 316;</code>
         */
        ISO_A4X7(86, 316),
        /**
         * <code>ISO_A4X8 = 317;</code>
         */
        ISO_A4X8(87, 317),
        /**
         * <code>ISO_A4X9 = 318;</code>
         */
        ISO_A4X9(88, 318),
        /**
         * <code>ISO_A3_EXTRA = 319;</code>
         */
        ISO_A3_EXTRA(89, 319),
        /**
         * <code>ISO_A2 = 320;</code>
         */
        ISO_A2(90, 320),
        /**
         * <code>ISO_A3X3 = 321;</code>
         */
        ISO_A3X3(91, 321),
        /**
         * <code>ISO_A3X4 = 322;</code>
         */
        ISO_A3X4(92, 322),
        /**
         * <code>ISO_A3X5 = 323;</code>
         */
        ISO_A3X5(93, 323),
        /**
         * <code>ISO_A3X6 = 324;</code>
         */
        ISO_A3X6(94, 324),
        /**
         * <code>ISO_A3X7 = 325;</code>
         */
        ISO_A3X7(95, 325),
        /**
         * <code>ISO_A1 = 326;</code>
         */
        ISO_A1(96, 326),
        /**
         * <code>ISO_A2X3 = 327;</code>
         */
        ISO_A2X3(97, 327),
        /**
         * <code>ISO_A2X4 = 328;</code>
         */
        ISO_A2X4(98, 328),
        /**
         * <code>ISO_A2X5 = 329;</code>
         */
        ISO_A2X5(99, 329),
        /**
         * <code>ISO_A0 = 330;</code>
         */
        ISO_A0(100, 330),
        /**
         * <code>ISO_A1X3 = 331;</code>
         */
        ISO_A1X3(101, 331),
        /**
         * <code>ISO_A1X4 = 332;</code>
         */
        ISO_A1X4(102, 332),
        /**
         * <code>ISO_2A0 = 333;</code>
         */
        ISO_2A0(103, 333),
        /**
         * <code>ISO_A0X3 = 334;</code>
         */
        ISO_A0X3(104, 334),
        /**
         * <code>ISO_B10 = 335;</code>
         */
        ISO_B10(105, 335),
        /**
         * <code>ISO_B9 = 336;</code>
         */
        ISO_B9(106, 336),
        /**
         * <code>ISO_B8 = 337;</code>
         */
        ISO_B8(107, 337),
        /**
         * <code>ISO_B7 = 338;</code>
         */
        ISO_B7(108, 338),
        /**
         * <code>ISO_B6 = 339;</code>
         */
        ISO_B6(109, 339),
        /**
         * <code>ISO_B6C4 = 340;</code>
         */
        ISO_B6C4(110, 340),
        /**
         * <code>ISO_B5 = 341;</code>
         */
        ISO_B5(111, 341),
        /**
         * <code>ISO_B5_EXTRA = 342;</code>
         */
        ISO_B5_EXTRA(112, 342),
        /**
         * <code>ISO_B4 = 343;</code>
         */
        ISO_B4(113, 343),
        /**
         * <code>ISO_B3 = 344;</code>
         */
        ISO_B3(114, 344),
        /**
         * <code>ISO_B2 = 345;</code>
         */
        ISO_B2(115, 345),
        /**
         * <code>ISO_B1 = 346;</code>
         */
        ISO_B1(116, 346),
        /**
         * <code>ISO_B0 = 347;</code>
         */
        ISO_B0(117, 347),
        /**
         * <code>ISO_C10 = 348;</code>
         */
        ISO_C10(118, 348),
        /**
         * <code>ISO_C9 = 349;</code>
         */
        ISO_C9(119, 349),
        /**
         * <code>ISO_C8 = 350;</code>
         */
        ISO_C8(120, 350),
        /**
         * <code>ISO_C7 = 351;</code>
         */
        ISO_C7(121, 351),
        /**
         * <code>ISO_C7C6 = 352;</code>
         */
        ISO_C7C6(122, 352),
        /**
         * <code>ISO_C6 = 353;</code>
         */
        ISO_C6(123, 353),
        /**
         * <code>ISO_C6C5 = 354;</code>
         */
        ISO_C6C5(124, 354),
        /**
         * <code>ISO_C5 = 355;</code>
         */
        ISO_C5(125, 355),
        /**
         * <code>ISO_C4 = 356;</code>
         */
        ISO_C4(126, 356),
        /**
         * <code>ISO_C3 = 357;</code>
         */
        ISO_C3(127, 357),
        /**
         * <code>ISO_C2 = 358;</code>
         */
        ISO_C2(128, 358),
        /**
         * <code>ISO_C1 = 359;</code>
         */
        ISO_C1(129, 359),
        /**
         * <code>ISO_C0 = 360;</code>
         */
        ISO_C0(130, 360),
        /**
         * <code>ISO_DL = 361;</code>
         */
        ISO_DL(131, 361),
        /**
         * <code>ISO_RA2 = 362;</code>
         */
        ISO_RA2(132, 362),
        /**
         * <code>ISO_SRA2 = 363;</code>
         */
        ISO_SRA2(133, 363),
        /**
         * <code>ISO_RA1 = 364;</code>
         */
        ISO_RA1(134, 364),
        /**
         * <code>ISO_SRA1 = 365;</code>
         */
        ISO_SRA1(135, 365),
        /**
         * <code>ISO_RA0 = 366;</code>
         */
        ISO_RA0(136, 366),
        /**
         * <code>ISO_SRA0 = 367;</code>
         */
        ISO_SRA0(137, 367),
        /**
         * <code>JIS_B10 = 400;</code>
         *
         * <pre>
         * Japanese standard sheet media size names.
         * </pre>
         */
        JIS_B10(138, 400),
        /**
         * <code>JIS_B9 = 401;</code>
         */
        JIS_B9(139, 401),
        /**
         * <code>JIS_B8 = 402;</code>
         */
        JIS_B8(140, 402),
        /**
         * <code>JIS_B7 = 403;</code>
         */
        JIS_B7(141, 403),
        /**
         * <code>JIS_B6 = 404;</code>
         */
        JIS_B6(142, 404),
        /**
         * <code>JIS_B5 = 405;</code>
         */
        JIS_B5(143, 405),
        /**
         * <code>JIS_B4 = 406;</code>
         */
        JIS_B4(144, 406),
        /**
         * <code>JIS_B3 = 407;</code>
         */
        JIS_B3(145, 407),
        /**
         * <code>JIS_B2 = 408;</code>
         */
        JIS_B2(146, 408),
        /**
         * <code>JIS_B1 = 409;</code>
         */
        JIS_B1(147, 409),
        /**
         * <code>JIS_B0 = 410;</code>
         */
        JIS_B0(148, 410),
        /**
         * <code>JIS_EXEC = 411;</code>
         */
        JIS_EXEC(149, 411),
        /**
         * <code>JPN_CHOU4 = 412;</code>
         */
        JPN_CHOU4(150, 412),
        /**
         * <code>JPN_HAGAKI = 413;</code>
         */
        JPN_HAGAKI(151, 413),
        /**
         * <code>JPN_YOU4 = 414;</code>
         */
        JPN_YOU4(152, 414),
        /**
         * <code>JPN_CHOU2 = 415;</code>
         */
        JPN_CHOU2(153, 415),
        /**
         * <code>JPN_CHOU3 = 416;</code>
         */
        JPN_CHOU3(154, 416),
        /**
         * <code>JPN_OUFUKU = 417;</code>
         */
        JPN_OUFUKU(155, 417),
        /**
         * <code>JPN_KAHU = 418;</code>
         */
        JPN_KAHU(156, 418),
        /**
         * <code>JPN_KAKU2 = 419;</code>
         */
        JPN_KAKU2(157, 419),
        /**
         * <code>OM_SMALL_PHOTO = 500;</code>
         *
         * <pre>
         * Other metric standard sheet media size names.
         * </pre>
         */
        OM_SMALL_PHOTO(158, 500),
        /**
         * <code>OM_ITALIAN = 501;</code>
         */
        OM_ITALIAN(159, 501),
        /**
         * <code>OM_POSTFIX = 502;</code>
         */
        OM_POSTFIX(160, 502),
        /**
         * <code>OM_LARGE_PHOTO = 503;</code>
         */
        OM_LARGE_PHOTO(161, 503),
        /**
         * <code>OM_FOLIO = 504;</code>
         */
        OM_FOLIO(162, 504),
        /**
         * <code>OM_FOLIO_SP = 505;</code>
         */
        OM_FOLIO_SP(163, 505),
        /**
         * <code>OM_INVITE = 506;</code>
         */
        OM_INVITE(164, 506),
        ;

        /**
         * <code>CUSTOM = 0;</code>
         */
        public static final int CUSTOM_VALUE = 0;
        /**
         * <code>NA_INDEX_3X5 = 100;</code>
         *
         * <pre>
         * North American standard sheet media names.
         * </pre>
         */
        public static final int NA_INDEX_3X5_VALUE = 100;
        /**
         * <code>NA_PERSONAL = 101;</code>
         */
        public static final int NA_PERSONAL_VALUE = 101;
        /**
         * <code>NA_MONARCH = 102;</code>
         */
        public static final int NA_MONARCH_VALUE = 102;
        /**
         * <code>NA_NUMBER_9 = 103;</code>
         */
        public static final int NA_NUMBER_9_VALUE = 103;
        /**
         * <code>NA_INDEX_4X6 = 104;</code>
         */
        public static final int NA_INDEX_4X6_VALUE = 104;
        /**
         * <code>NA_NUMBER_10 = 105;</code>
         */
        public static final int NA_NUMBER_10_VALUE = 105;
        /**
         * <code>NA_A2 = 106;</code>
         */
        public static final int NA_A2_VALUE = 106;
        /**
         * <code>NA_NUMBER_11 = 107;</code>
         */
        public static final int NA_NUMBER_11_VALUE = 107;
        /**
         * <code>NA_NUMBER_12 = 108;</code>
         */
        public static final int NA_NUMBER_12_VALUE = 108;
        /**
         * <code>NA_5X7 = 109;</code>
         */
        public static final int NA_5X7_VALUE = 109;
        /**
         * <code>NA_INDEX_5X8 = 110;</code>
         */
        public static final int NA_INDEX_5X8_VALUE = 110;
        /**
         * <code>NA_NUMBER_14 = 111;</code>
         */
        public static final int NA_NUMBER_14_VALUE = 111;
        /**
         * <code>NA_INVOICE = 112;</code>
         */
        public static final int NA_INVOICE_VALUE = 112;
        /**
         * <code>NA_INDEX_4X6_EXT = 113;</code>
         */
        public static final int NA_INDEX_4X6_EXT_VALUE = 113;
        /**
         * <code>NA_6X9 = 114;</code>
         */
        public static final int NA_6X9_VALUE = 114;
        /**
         * <code>NA_C5 = 115;</code>
         */
        public static final int NA_C5_VALUE = 115;
        /**
         * <code>NA_7X9 = 116;</code>
         */
        public static final int NA_7X9_VALUE = 116;
        /**
         * <code>NA_EXECUTIVE = 117;</code>
         */
        public static final int NA_EXECUTIVE_VALUE = 117;
        /**
         * <code>NA_GOVT_LETTER = 118;</code>
         */
        public static final int NA_GOVT_LETTER_VALUE = 118;
        /**
         * <code>NA_GOVT_LEGAL = 119;</code>
         */
        public static final int NA_GOVT_LEGAL_VALUE = 119;
        /**
         * <code>NA_QUARTO = 120;</code>
         */
        public static final int NA_QUARTO_VALUE = 120;
        /**
         * <code>NA_LETTER = 121;</code>
         */
        public static final int NA_LETTER_VALUE = 121;
        /**
         * <code>NA_FANFOLD_EUR = 122;</code>
         */
        public static final int NA_FANFOLD_EUR_VALUE = 122;
        /**
         * <code>NA_LETTER_PLUS = 123;</code>
         */
        public static final int NA_LETTER_PLUS_VALUE = 123;
        /**
         * <code>NA_FOOLSCAP = 124;</code>
         */
        public static final int NA_FOOLSCAP_VALUE = 124;
        /**
         * <code>NA_LEGAL = 125;</code>
         */
        public static final int NA_LEGAL_VALUE = 125;
        /**
         * <code>NA_SUPER_A = 126;</code>
         */
        public static final int NA_SUPER_A_VALUE = 126;
        /**
         * <code>NA_9X11 = 127;</code>
         */
        public static final int NA_9X11_VALUE = 127;
        /**
         * <code>NA_ARCH_A = 128;</code>
         */
        public static final int NA_ARCH_A_VALUE = 128;
        /**
         * <code>NA_LETTER_EXTRA = 129;</code>
         */
        public static final int NA_LETTER_EXTRA_VALUE = 129;
        /**
         * <code>NA_LEGAL_EXTRA = 130;</code>
         */
        public static final int NA_LEGAL_EXTRA_VALUE = 130;
        /**
         * <code>NA_10X11 = 131;</code>
         */
        public static final int NA_10X11_VALUE = 131;
        /**
         * <code>NA_10X13 = 132;</code>
         */
        public static final int NA_10X13_VALUE = 132;
        /**
         * <code>NA_10X14 = 133;</code>
         */
        public static final int NA_10X14_VALUE = 133;
        /**
         * <code>NA_10X15 = 134;</code>
         */
        public static final int NA_10X15_VALUE = 134;
        /**
         * <code>NA_11X12 = 135;</code>
         */
        public static final int NA_11X12_VALUE = 135;
        /**
         * <code>NA_EDP = 136;</code>
         */
        public static final int NA_EDP_VALUE = 136;
        /**
         * <code>NA_FANFOLD_US = 137;</code>
         */
        public static final int NA_FANFOLD_US_VALUE = 137;
        /**
         * <code>NA_11X15 = 138;</code>
         */
        public static final int NA_11X15_VALUE = 138;
        /**
         * <code>NA_LEDGER = 139;</code>
         */
        public static final int NA_LEDGER_VALUE = 139;
        /**
         * <code>NA_EUR_EDP = 140;</code>
         */
        public static final int NA_EUR_EDP_VALUE = 140;
        /**
         * <code>NA_ARCH_B = 141;</code>
         */
        public static final int NA_ARCH_B_VALUE = 141;
        /**
         * <code>NA_12X19 = 142;</code>
         */
        public static final int NA_12X19_VALUE = 142;
        /**
         * <code>NA_B_PLUS = 143;</code>
         */
        public static final int NA_B_PLUS_VALUE = 143;
        /**
         * <code>NA_SUPER_B = 144;</code>
         */
        public static final int NA_SUPER_B_VALUE = 144;
        /**
         * <code>NA_C = 145;</code>
         */
        public static final int NA_C_VALUE = 145;
        /**
         * <code>NA_ARCH_C = 146;</code>
         */
        public static final int NA_ARCH_C_VALUE = 146;
        /**
         * <code>NA_D = 147;</code>
         */
        public static final int NA_D_VALUE = 147;
        /**
         * <code>NA_ARCH_D = 148;</code>
         */
        public static final int NA_ARCH_D_VALUE = 148;
        /**
         * <code>NA_ASME_F = 149;</code>
         */
        public static final int NA_ASME_F_VALUE = 149;
        /**
         * <code>NA_WIDE_FORMAT = 150;</code>
         */
        public static final int NA_WIDE_FORMAT_VALUE = 150;
        /**
         * <code>NA_E = 151;</code>
         */
        public static final int NA_E_VALUE = 151;
        /**
         * <code>NA_ARCH_E = 152;</code>
         */
        public static final int NA_ARCH_E_VALUE = 152;
        /**
         * <code>NA_F = 153;</code>
         */
        public static final int NA_F_VALUE = 153;
        /**
         * <code>ROC_16K = 200;</code>
         *
         * <pre>
         * Chinese standard sheet media size names.
         * </pre>
         */
        public static final int ROC_16K_VALUE = 200;
        /**
         * <code>ROC_8K = 201;</code>
         */
        public static final int ROC_8K_VALUE = 201;
        /**
         * <code>PRC_32K = 202;</code>
         */
        public static final int PRC_32K_VALUE = 202;
        /**
         * <code>PRC_1 = 203;</code>
         */
        public static final int PRC_1_VALUE = 203;
        /**
         * <code>PRC_2 = 204;</code>
         */
        public static final int PRC_2_VALUE = 204;
        /**
         * <code>PRC_4 = 205;</code>
         */
        public static final int PRC_4_VALUE = 205;
        /**
         * <code>PRC_5 = 206;</code>
         */
        public static final int PRC_5_VALUE = 206;
        /**
         * <code>PRC_8 = 207;</code>
         */
        public static final int PRC_8_VALUE = 207;
        /**
         * <code>PRC_6 = 208;</code>
         */
        public static final int PRC_6_VALUE = 208;
        /**
         * <code>PRC_3 = 209;</code>
         */
        public static final int PRC_3_VALUE = 209;
        /**
         * <code>PRC_16K = 210;</code>
         */
        public static final int PRC_16K_VALUE = 210;
        /**
         * <code>PRC_7 = 211;</code>
         */
        public static final int PRC_7_VALUE = 211;
        /**
         * <code>OM_JUURO_KU_KAI = 212;</code>
         */
        public static final int OM_JUURO_KU_KAI_VALUE = 212;
        /**
         * <code>OM_PA_KAI = 213;</code>
         */
        public static final int OM_PA_KAI_VALUE = 213;
        /**
         * <code>OM_DAI_PA_KAI = 214;</code>
         */
        public static final int OM_DAI_PA_KAI_VALUE = 214;
        /**
         * <code>PRC_10 = 215;</code>
         */
        public static final int PRC_10_VALUE = 215;
        /**
         * <code>ISO_A10 = 301;</code>
         *
         * <pre>
         * ISO standard sheet media size names.
         * </pre>
         */
        public static final int ISO_A10_VALUE = 301;
        /**
         * <code>ISO_A9 = 302;</code>
         */
        public static final int ISO_A9_VALUE = 302;
        /**
         * <code>ISO_A8 = 303;</code>
         */
        public static final int ISO_A8_VALUE = 303;
        /**
         * <code>ISO_A7 = 304;</code>
         */
        public static final int ISO_A7_VALUE = 304;
        /**
         * <code>ISO_A6 = 305;</code>
         */
        public static final int ISO_A6_VALUE = 305;
        /**
         * <code>ISO_A5 = 306;</code>
         */
        public static final int ISO_A5_VALUE = 306;
        /**
         * <code>ISO_A5_EXTRA = 307;</code>
         */
        public static final int ISO_A5_EXTRA_VALUE = 307;
        /**
         * <code>ISO_A4 = 308;</code>
         */
        public static final int ISO_A4_VALUE = 308;
        /**
         * <code>ISO_A4_TAB = 309;</code>
         */
        public static final int ISO_A4_TAB_VALUE = 309;
        /**
         * <code>ISO_A4_EXTRA = 310;</code>
         */
        public static final int ISO_A4_EXTRA_VALUE = 310;
        /**
         * <code>ISO_A3 = 311;</code>
         */
        public static final int ISO_A3_VALUE = 311;
        /**
         * <code>ISO_A4X3 = 312;</code>
         */
        public static final int ISO_A4X3_VALUE = 312;
        /**
         * <code>ISO_A4X4 = 313;</code>
         */
        public static final int ISO_A4X4_VALUE = 313;
        /**
         * <code>ISO_A4X5 = 314;</code>
         */
        public static final int ISO_A4X5_VALUE = 314;
        /**
         * <code>ISO_A4X6 = 315;</code>
         */
        public static final int ISO_A4X6_VALUE = 315;
        /**
         * <code>ISO_A4X7 = 316;</code>
         */
        public static final int ISO_A4X7_VALUE = 316;
        /**
         * <code>ISO_A4X8 = 317;</code>
         */
        public static final int ISO_A4X8_VALUE = 317;
        /**
         * <code>ISO_A4X9 = 318;</code>
         */
        public static final int ISO_A4X9_VALUE = 318;
        /**
         * <code>ISO_A3_EXTRA = 319;</code>
         */
        public static final int ISO_A3_EXTRA_VALUE = 319;
        /**
         * <code>ISO_A2 = 320;</code>
         */
        public static final int ISO_A2_VALUE = 320;
        /**
         * <code>ISO_A3X3 = 321;</code>
         */
        public static final int ISO_A3X3_VALUE = 321;
        /**
         * <code>ISO_A3X4 = 322;</code>
         */
        public static final int ISO_A3X4_VALUE = 322;
        /**
         * <code>ISO_A3X5 = 323;</code>
         */
        public static final int ISO_A3X5_VALUE = 323;
        /**
         * <code>ISO_A3X6 = 324;</code>
         */
        public static final int ISO_A3X6_VALUE = 324;
        /**
         * <code>ISO_A3X7 = 325;</code>
         */
        public static final int ISO_A3X7_VALUE = 325;
        /**
         * <code>ISO_A1 = 326;</code>
         */
        public static final int ISO_A1_VALUE = 326;
        /**
         * <code>ISO_A2X3 = 327;</code>
         */
        public static final int ISO_A2X3_VALUE = 327;
        /**
         * <code>ISO_A2X4 = 328;</code>
         */
        public static final int ISO_A2X4_VALUE = 328;
        /**
         * <code>ISO_A2X5 = 329;</code>
         */
        public static final int ISO_A2X5_VALUE = 329;
        /**
         * <code>ISO_A0 = 330;</code>
         */
        public static final int ISO_A0_VALUE = 330;
        /**
         * <code>ISO_A1X3 = 331;</code>
         */
        public static final int ISO_A1X3_VALUE = 331;
        /**
         * <code>ISO_A1X4 = 332;</code>
         */
        public static final int ISO_A1X4_VALUE = 332;
        /**
         * <code>ISO_2A0 = 333;</code>
         */
        public static final int ISO_2A0_VALUE = 333;
        /**
         * <code>ISO_A0X3 = 334;</code>
         */
        public static final int ISO_A0X3_VALUE = 334;
        /**
         * <code>ISO_B10 = 335;</code>
         */
        public static final int ISO_B10_VALUE = 335;
        /**
         * <code>ISO_B9 = 336;</code>
         */
        public static final int ISO_B9_VALUE = 336;
        /**
         * <code>ISO_B8 = 337;</code>
         */
        public static final int ISO_B8_VALUE = 337;
        /**
         * <code>ISO_B7 = 338;</code>
         */
        public static final int ISO_B7_VALUE = 338;
        /**
         * <code>ISO_B6 = 339;</code>
         */
        public static final int ISO_B6_VALUE = 339;
        /**
         * <code>ISO_B6C4 = 340;</code>
         */
        public static final int ISO_B6C4_VALUE = 340;
        /**
         * <code>ISO_B5 = 341;</code>
         */
        public static final int ISO_B5_VALUE = 341;
        /**
         * <code>ISO_B5_EXTRA = 342;</code>
         */
        public static final int ISO_B5_EXTRA_VALUE = 342;
        /**
         * <code>ISO_B4 = 343;</code>
         */
        public static final int ISO_B4_VALUE = 343;
        /**
         * <code>ISO_B3 = 344;</code>
         */
        public static final int ISO_B3_VALUE = 344;
        /**
         * <code>ISO_B2 = 345;</code>
         */
        public static final int ISO_B2_VALUE = 345;
        /**
         * <code>ISO_B1 = 346;</code>
         */
        public static final int ISO_B1_VALUE = 346;
        /**
         * <code>ISO_B0 = 347;</code>
         */
        public static final int ISO_B0_VALUE = 347;
        /**
         * <code>ISO_C10 = 348;</code>
         */
        public static final int ISO_C10_VALUE = 348;
        /**
         * <code>ISO_C9 = 349;</code>
         */
        public static final int ISO_C9_VALUE = 349;
        /**
         * <code>ISO_C8 = 350;</code>
         */
        public static final int ISO_C8_VALUE = 350;
        /**
         * <code>ISO_C7 = 351;</code>
         */
        public static final int ISO_C7_VALUE = 351;
        /**
         * <code>ISO_C7C6 = 352;</code>
         */
        public static final int ISO_C7C6_VALUE = 352;
        /**
         * <code>ISO_C6 = 353;</code>
         */
        public static final int ISO_C6_VALUE = 353;
        /**
         * <code>ISO_C6C5 = 354;</code>
         */
        public static final int ISO_C6C5_VALUE = 354;
        /**
         * <code>ISO_C5 = 355;</code>
         */
        public static final int ISO_C5_VALUE = 355;
        /**
         * <code>ISO_C4 = 356;</code>
         */
        public static final int ISO_C4_VALUE = 356;
        /**
         * <code>ISO_C3 = 357;</code>
         */
        public static final int ISO_C3_VALUE = 357;
        /**
         * <code>ISO_C2 = 358;</code>
         */
        public static final int ISO_C2_VALUE = 358;
        /**
         * <code>ISO_C1 = 359;</code>
         */
        public static final int ISO_C1_VALUE = 359;
        /**
         * <code>ISO_C0 = 360;</code>
         */
        public static final int ISO_C0_VALUE = 360;
        /**
         * <code>ISO_DL = 361;</code>
         */
        public static final int ISO_DL_VALUE = 361;
        /**
         * <code>ISO_RA2 = 362;</code>
         */
        public static final int ISO_RA2_VALUE = 362;
        /**
         * <code>ISO_SRA2 = 363;</code>
         */
        public static final int ISO_SRA2_VALUE = 363;
        /**
         * <code>ISO_RA1 = 364;</code>
         */
        public static final int ISO_RA1_VALUE = 364;
        /**
         * <code>ISO_SRA1 = 365;</code>
         */
        public static final int ISO_SRA1_VALUE = 365;
        /**
         * <code>ISO_RA0 = 366;</code>
         */
        public static final int ISO_RA0_VALUE = 366;
        /**
         * <code>ISO_SRA0 = 367;</code>
         */
        public static final int ISO_SRA0_VALUE = 367;
        /**
         * <code>JIS_B10 = 400;</code>
         *
         * <pre>
         * Japanese standard sheet media size names.
         * </pre>
         */
        public static final int JIS_B10_VALUE = 400;
        /**
         * <code>JIS_B9 = 401;</code>
         */
        public static final int JIS_B9_VALUE = 401;
        /**
         * <code>JIS_B8 = 402;</code>
         */
        public static final int JIS_B8_VALUE = 402;
        /**
         * <code>JIS_B7 = 403;</code>
         */
        public static final int JIS_B7_VALUE = 403;
        /**
         * <code>JIS_B6 = 404;</code>
         */
        public static final int JIS_B6_VALUE = 404;
        /**
         * <code>JIS_B5 = 405;</code>
         */
        public static final int JIS_B5_VALUE = 405;
        /**
         * <code>JIS_B4 = 406;</code>
         */
        public static final int JIS_B4_VALUE = 406;
        /**
         * <code>JIS_B3 = 407;</code>
         */
        public static final int JIS_B3_VALUE = 407;
        /**
         * <code>JIS_B2 = 408;</code>
         */
        public static final int JIS_B2_VALUE = 408;
        /**
         * <code>JIS_B1 = 409;</code>
         */
        public static final int JIS_B1_VALUE = 409;
        /**
         * <code>JIS_B0 = 410;</code>
         */
        public static final int JIS_B0_VALUE = 410;
        /**
         * <code>JIS_EXEC = 411;</code>
         */
        public static final int JIS_EXEC_VALUE = 411;
        /**
         * <code>JPN_CHOU4 = 412;</code>
         */
        public static final int JPN_CHOU4_VALUE = 412;
        /**
         * <code>JPN_HAGAKI = 413;</code>
         */
        public static final int JPN_HAGAKI_VALUE = 413;
        /**
         * <code>JPN_YOU4 = 414;</code>
         */
        public static final int JPN_YOU4_VALUE = 414;
        /**
         * <code>JPN_CHOU2 = 415;</code>
         */
        public static final int JPN_CHOU2_VALUE = 415;
        /**
         * <code>JPN_CHOU3 = 416;</code>
         */
        public static final int JPN_CHOU3_VALUE = 416;
        /**
         * <code>JPN_OUFUKU = 417;</code>
         */
        public static final int JPN_OUFUKU_VALUE = 417;
        /**
         * <code>JPN_KAHU = 418;</code>
         */
        public static final int JPN_KAHU_VALUE = 418;
        /**
         * <code>JPN_KAKU2 = 419;</code>
         */
        public static final int JPN_KAKU2_VALUE = 419;
        /**
         * <code>OM_SMALL_PHOTO = 500;</code>
         *
         * <pre>
         * Other metric standard sheet media size names.
         * </pre>
         */
        public static final int OM_SMALL_PHOTO_VALUE = 500;
        /**
         * <code>OM_ITALIAN = 501;</code>
         */
        public static final int OM_ITALIAN_VALUE = 501;
        /**
         * <code>OM_POSTFIX = 502;</code>
         */
        public static final int OM_POSTFIX_VALUE = 502;
        /**
         * <code>OM_LARGE_PHOTO = 503;</code>
         */
        public static final int OM_LARGE_PHOTO_VALUE = 503;
        /**
         * <code>OM_FOLIO = 504;</code>
         */
        public static final int OM_FOLIO_VALUE = 504;
        /**
         * <code>OM_FOLIO_SP = 505;</code>
         */
        public static final int OM_FOLIO_SP_VALUE = 505;
        /**
         * <code>OM_INVITE = 506;</code>
         */
        public static final int OM_INVITE_VALUE = 506;


        public final int getNumber() { return value; }

        public static Name valueOf(int value) {
            switch (value) {
                case 0: return CUSTOM;
                case 100: return NA_INDEX_3X5;
                case 101: return NA_PERSONAL;
                case 102: return NA_MONARCH;
                case 103: return NA_NUMBER_9;
                case 104: return NA_INDEX_4X6;
                case 105: return NA_NUMBER_10;
                case 106: return NA_A2;
                case 107: return NA_NUMBER_11;
                case 108: return NA_NUMBER_12;
                case 109: return NA_5X7;
                case 110: return NA_INDEX_5X8;
                case 111: return NA_NUMBER_14;
                case 112: return NA_INVOICE;
                case 113: return NA_INDEX_4X6_EXT;
                case 114: return NA_6X9;
                case 115: return NA_C5;
                case 116: return NA_7X9;
                case 117: return NA_EXECUTIVE;
                case 118: return NA_GOVT_LETTER;
                case 119: return NA_GOVT_LEGAL;
                case 120: return NA_QUARTO;
                case 121: return NA_LETTER;
                case 122: return NA_FANFOLD_EUR;
                case 123: return NA_LETTER_PLUS;
                case 124: return NA_FOOLSCAP;
                case 125: return NA_LEGAL;
                case 126: return NA_SUPER_A;
                case 127: return NA_9X11;
                case 128: return NA_ARCH_A;
                case 129: return NA_LETTER_EXTRA;
                case 130: return NA_LEGAL_EXTRA;
                case 131: return NA_10X11;
                case 132: return NA_10X13;
                case 133: return NA_10X14;
                case 134: return NA_10X15;
                case 135: return NA_11X12;
                case 136: return NA_EDP;
                case 137: return NA_FANFOLD_US;
                case 138: return NA_11X15;
                case 139: return NA_LEDGER;
                case 140: return NA_EUR_EDP;
                case 141: return NA_ARCH_B;
                case 142: return NA_12X19;
                case 143: return NA_B_PLUS;
                case 144: return NA_SUPER_B;
                case 145: return NA_C;
                case 146: return NA_ARCH_C;
                case 147: return NA_D;
                case 148: return NA_ARCH_D;
                case 149: return NA_ASME_F;
                case 150: return NA_WIDE_FORMAT;
                case 151: return NA_E;
                case 152: return NA_ARCH_E;
                case 153: return NA_F;
                case 200: return ROC_16K;
                case 201: return ROC_8K;
                case 202: return PRC_32K;
                case 203: return PRC_1;
                case 204: return PRC_2;
                case 205: return PRC_4;
                case 206: return PRC_5;
                case 207: return PRC_8;
                case 208: return PRC_6;
                case 209: return PRC_3;
                case 210: return PRC_16K;
                case 211: return PRC_7;
                case 212: return OM_JUURO_KU_KAI;
                case 213: return OM_PA_KAI;
                case 214: return OM_DAI_PA_KAI;
                case 215: return PRC_10;
                case 301: return ISO_A10;
                case 302: return ISO_A9;
                case 303: return ISO_A8;
                case 304: return ISO_A7;
                case 305: return ISO_A6;
                case 306: return ISO_A5;
                case 307: return ISO_A5_EXTRA;
                case 308: return ISO_A4;
                case 309: return ISO_A4_TAB;
                case 310: return ISO_A4_EXTRA;
                case 311: return ISO_A3;
                case 312: return ISO_A4X3;
                case 313: return ISO_A4X4;
                case 314: return ISO_A4X5;
                case 315: return ISO_A4X6;
                case 316: return ISO_A4X7;
                case 317: return ISO_A4X8;
                case 318: return ISO_A4X9;
                case 319: return ISO_A3_EXTRA;
                case 320: return ISO_A2;
                case 321: return ISO_A3X3;
                case 322: return ISO_A3X4;
                case 323: return ISO_A3X5;
                case 324: return ISO_A3X6;
                case 325: return ISO_A3X7;
                case 326: return ISO_A1;
                case 327: return ISO_A2X3;
                case 328: return ISO_A2X4;
                case 329: return ISO_A2X5;
                case 330: return ISO_A0;
                case 331: return ISO_A1X3;
                case 332: return ISO_A1X4;
                case 333: return ISO_2A0;
                case 334: return ISO_A0X3;
                case 335: return ISO_B10;
                case 336: return ISO_B9;
                case 337: return ISO_B8;
                case 338: return ISO_B7;
                case 339: return ISO_B6;
                case 340: return ISO_B6C4;
                case 341: return ISO_B5;
                case 342: return ISO_B5_EXTRA;
                case 343: return ISO_B4;
                case 344: return ISO_B3;
                case 345: return ISO_B2;
                case 346: return ISO_B1;
                case 347: return ISO_B0;
                case 348: return ISO_C10;
                case 349: return ISO_C9;
                case 350: return ISO_C8;
                case 351: return ISO_C7;
                case 352: return ISO_C7C6;
                case 353: return ISO_C6;
                case 354: return ISO_C6C5;
                case 355: return ISO_C5;
                case 356: return ISO_C4;
                case 357: return ISO_C3;
                case 358: return ISO_C2;
                case 359: return ISO_C1;
                case 360: return ISO_C0;
                case 361: return ISO_DL;
                case 362: return ISO_RA2;
                case 363: return ISO_SRA2;
                case 364: return ISO_RA1;
                case 365: return ISO_SRA1;
                case 366: return ISO_RA0;
                case 367: return ISO_SRA0;
                case 400: return JIS_B10;
                case 401: return JIS_B9;
                case 402: return JIS_B8;
                case 403: return JIS_B7;
                case 404: return JIS_B6;
                case 405: return JIS_B5;
                case 406: return JIS_B4;
                case 407: return JIS_B3;
                case 408: return JIS_B2;
                case 409: return JIS_B1;
                case 410: return JIS_B0;
                case 411: return JIS_EXEC;
                case 412: return JPN_CHOU4;
                case 413: return JPN_HAGAKI;
                case 414: return JPN_YOU4;
                case 415: return JPN_CHOU2;
                case 416: return JPN_CHOU3;
                case 417: return JPN_OUFUKU;
                case 418: return JPN_KAHU;
                case 419: return JPN_KAKU2;
                case 500: return OM_SMALL_PHOTO;
                case 501: return OM_ITALIAN;
                case 502: return OM_POSTFIX;
                case 503: return OM_LARGE_PHOTO;
                case 504: return OM_FOLIO;
                case 505: return OM_FOLIO_SP;
                case 506: return OM_INVITE;
                default: return null;
            }
        }

        private static final Name[] VALUES = values();

        private final int index;
        private final int value;

        private Name(int index, int value) {
            this.index = index;
            this.value = value;
        }

    }

    public class Option {
        public final Name name                                                  = Name.CUSTOM; /* default = CUSTOM */
        public final Integer width_microns                                      = null;
        public final Integer height_microns                                     = null;
        public final boolean is_continuous_feed                                 = false; /* default = FALSE */
        public final boolean is_default                                         = false; /* default = FALSE */
        public final String custom_display_name                                 = null;
        public final String vendor_id                                           = null;
        public final List<LocalizedString> custom_display_name_localized        = null;

        @Override
        public String toString() {
            return "Option{" +
                    "name=" + name +
                    ", width_microns=" + width_microns +
                    ", height_microns=" + height_microns +
                    ", is_continuous_feed=" + is_continuous_feed +
                    ", is_default=" + is_default +
                    ", custom_display_name='" + custom_display_name + '\'' +
                    ", vendor_id='" + vendor_id + '\'' +
                    ", custom_display_name_localized=" + custom_display_name_localized +
                    '}';
        }
    }

    public final List<Option> option            = null;
    public final Integer max_width_microns      = null;
    public final Integer max_height_microns     = null;
    public final Integer min_width_microns      = null;
    public final Integer min_height_microns     = null;

    @Override
    public String toString() {
        return "MediaSize{" +
                "option=" + option.toString() +
                ", max_width_microns=" + max_width_microns +
                ", max_height_microns=" + max_height_microns +
                ", min_width_microns=" + min_width_microns +
                ", min_height_microns=" + min_height_microns +
                '}';
    }
}
