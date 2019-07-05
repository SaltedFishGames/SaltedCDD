package cn.saltedfish.saltedcdd.game.pattern;

public enum EPatternType {
    Unrecognized, // 尚未进行识别
    Unknown, // 未知牌型

    // 1 张牌
    Single, // 单张

    // 2 张牌
    Pair, // 对子

    // 3 张牌
    Triple, // 三张

    // 4 张牌
    Quadruple, // 四张

    // 5 张牌
    Straight, // 顺子
    Flush, // 同花
    FullHouse, // 葫芦（三带二）
    FourOfAKind, // 铁支（四带一）
    StraightFlush, // 同花顺（同花 & 顺子）
}
