package cn.saltedfish.saltedcdd.stage.gameplay;

import android.graphics.Canvas;
import android.util.Log;

import java.util.EnumMap;

import cn.saltedfish.saltedcdd.R;
import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.game.card.ECardNumber;
import cn.saltedfish.saltedcdd.game.card.ECardSuit;

public class CardGameView extends SpiritGameView {
    protected Card mCard;

    public final static int sSmallWidth = 78; // 56
    public final static int sSmallHeight = 109; // 74

    public final static int sBigWidth = 168; // original 140
    public final static int sBigHeight = 224; // original 186

    public final static int sSelectYDelta = 50; // original 186

    protected ShowType mShowType;

    protected final static int mBigBackResId = R.drawable.card_back;

    protected boolean mSelected;

    protected static EnumMap<ECardSuit, EnumMap<ECardNumber, Integer>> sBigFrontResIds;

    protected static EnumMap<ECardSuit, EnumMap<ECardNumber, Integer>> sSmallFrontResIds;

    static
    {
        // big
        EnumMap<ECardNumber, Integer> numberMap = null;
        sBigFrontResIds = new EnumMap<>(ECardSuit.class);

        // big.club
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sBigFrontResIds.put(ECardSuit.CLUB, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.club_3_big);
        numberMap.put(ECardNumber.NUM_4, R.drawable.club_4_big);
        numberMap.put(ECardNumber.NUM_5, R.drawable.club_5_big);
        numberMap.put(ECardNumber.NUM_6, R.drawable.club_6_big);
        numberMap.put(ECardNumber.NUM_7, R.drawable.club_7_big);
        numberMap.put(ECardNumber.NUM_8, R.drawable.club_8_big);
        numberMap.put(ECardNumber.NUM_9, R.drawable.club_9_big);
        numberMap.put(ECardNumber.NUM_10, R.drawable.club_10_big);
        numberMap.put(ECardNumber.NUM_J, R.drawable.club_j_big);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.club_q_big);
        numberMap.put(ECardNumber.NUM_K, R.drawable.club_k_big);
        numberMap.put(ECardNumber.NUM_A, R.drawable.club_a_big);
        numberMap.put(ECardNumber.NUM_2, R.drawable.club_2_big);

        // big.diamond
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sBigFrontResIds.put(ECardSuit.DIAMOND, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.diamond_3_big);
        numberMap.put(ECardNumber.NUM_4, R.drawable.diamond_4_big);
        numberMap.put(ECardNumber.NUM_5, R.drawable.diamond_5_big);
        numberMap.put(ECardNumber.NUM_6, R.drawable.diamond_6_big);
        numberMap.put(ECardNumber.NUM_7, R.drawable.diamond_7_big);
        numberMap.put(ECardNumber.NUM_8, R.drawable.diamond_8_big);
        numberMap.put(ECardNumber.NUM_9, R.drawable.diamond_9_big);
        numberMap.put(ECardNumber.NUM_10, R.drawable.diamond_10_big);
        numberMap.put(ECardNumber.NUM_J, R.drawable.diamond_j_big);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.diamond_q_big);
        numberMap.put(ECardNumber.NUM_K, R.drawable.diamond_k_big);
        numberMap.put(ECardNumber.NUM_A, R.drawable.diamond_a_big);
        numberMap.put(ECardNumber.NUM_2, R.drawable.diamond_2_big);

        // big.heart
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sBigFrontResIds.put(ECardSuit.HEART, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.heart_3_big);
        numberMap.put(ECardNumber.NUM_4, R.drawable.heart_4_big);
        numberMap.put(ECardNumber.NUM_5, R.drawable.heart_5_big);
        numberMap.put(ECardNumber.NUM_6, R.drawable.heart_6_big);
        numberMap.put(ECardNumber.NUM_7, R.drawable.heart_7_big);
        numberMap.put(ECardNumber.NUM_8, R.drawable.heart_8_big);
        numberMap.put(ECardNumber.NUM_9, R.drawable.heart_9_big);
        numberMap.put(ECardNumber.NUM_10, R.drawable.heart_10_big);
        numberMap.put(ECardNumber.NUM_J, R.drawable.heart_j_big);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.heart_q_big);
        numberMap.put(ECardNumber.NUM_K, R.drawable.heart_k_big);
        numberMap.put(ECardNumber.NUM_A, R.drawable.heart_a_big);
        numberMap.put(ECardNumber.NUM_2, R.drawable.heart_2_big);

        // big.spade
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sBigFrontResIds.put(ECardSuit.SPADE, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.spade_3_big);
        numberMap.put(ECardNumber.NUM_4, R.drawable.spade_4_big);
        numberMap.put(ECardNumber.NUM_5, R.drawable.spade_5_big);
        numberMap.put(ECardNumber.NUM_6, R.drawable.spade_6_big);
        numberMap.put(ECardNumber.NUM_7, R.drawable.spade_7_big);
        numberMap.put(ECardNumber.NUM_8, R.drawable.spade_8_big);
        numberMap.put(ECardNumber.NUM_9, R.drawable.spade_9_big);
        numberMap.put(ECardNumber.NUM_10, R.drawable.spade_10_big);
        numberMap.put(ECardNumber.NUM_J, R.drawable.spade_j_big);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.spade_q_big);
        numberMap.put(ECardNumber.NUM_K, R.drawable.spade_k_big);
        numberMap.put(ECardNumber.NUM_A, R.drawable.spade_a_big);
        numberMap.put(ECardNumber.NUM_2, R.drawable.spade_2_big);


        // small
        sSmallFrontResIds = new EnumMap<>(ECardSuit.class);

        // small.club
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sSmallFrontResIds.put(ECardSuit.CLUB, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.club_3);
        numberMap.put(ECardNumber.NUM_4, R.drawable.club_4);
        numberMap.put(ECardNumber.NUM_5, R.drawable.club_5);
        numberMap.put(ECardNumber.NUM_6, R.drawable.club_6);
        numberMap.put(ECardNumber.NUM_7, R.drawable.club_7);
        numberMap.put(ECardNumber.NUM_8, R.drawable.club_8);
        numberMap.put(ECardNumber.NUM_9, R.drawable.club_9);
        numberMap.put(ECardNumber.NUM_10, R.drawable.club_10);
        numberMap.put(ECardNumber.NUM_J, R.drawable.club_j);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.club_q);
        numberMap.put(ECardNumber.NUM_K, R.drawable.club_k);
        numberMap.put(ECardNumber.NUM_A, R.drawable.club_a);
        numberMap.put(ECardNumber.NUM_2, R.drawable.club_2);

        // small.diamond
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sSmallFrontResIds.put(ECardSuit.DIAMOND, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.diamond_3);
        numberMap.put(ECardNumber.NUM_4, R.drawable.diamond_4);
        numberMap.put(ECardNumber.NUM_5, R.drawable.diamond_5);
        numberMap.put(ECardNumber.NUM_6, R.drawable.diamond_6);
        numberMap.put(ECardNumber.NUM_7, R.drawable.diamond_7);
        numberMap.put(ECardNumber.NUM_8, R.drawable.diamond_8);
        numberMap.put(ECardNumber.NUM_9, R.drawable.diamond_9);
        numberMap.put(ECardNumber.NUM_10, R.drawable.diamond_10);
        numberMap.put(ECardNumber.NUM_J, R.drawable.diamond_j);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.diamond_q);
        numberMap.put(ECardNumber.NUM_K, R.drawable.diamond_k);
        numberMap.put(ECardNumber.NUM_A, R.drawable.diamond_a);
        numberMap.put(ECardNumber.NUM_2, R.drawable.diamond_2);

        // small.heart
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sSmallFrontResIds.put(ECardSuit.HEART, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.heart_3);
        numberMap.put(ECardNumber.NUM_4, R.drawable.heart_4);
        numberMap.put(ECardNumber.NUM_5, R.drawable.heart_5);
        numberMap.put(ECardNumber.NUM_6, R.drawable.heart_6);
        numberMap.put(ECardNumber.NUM_7, R.drawable.heart_7);
        numberMap.put(ECardNumber.NUM_8, R.drawable.heart_8);
        numberMap.put(ECardNumber.NUM_9, R.drawable.heart_9);
        numberMap.put(ECardNumber.NUM_10, R.drawable.heart_10);
        numberMap.put(ECardNumber.NUM_J, R.drawable.heart_j);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.heart_q);
        numberMap.put(ECardNumber.NUM_K, R.drawable.heart_k);
        numberMap.put(ECardNumber.NUM_A, R.drawable.heart_a);
        numberMap.put(ECardNumber.NUM_2, R.drawable.heart_2);

        // small.spade
        numberMap = new EnumMap<ECardNumber, Integer>(ECardNumber.class);
        sSmallFrontResIds.put(ECardSuit.SPADE, numberMap);
        numberMap.put(ECardNumber.NUM_3, R.drawable.spade_3);
        numberMap.put(ECardNumber.NUM_4, R.drawable.spade_4);
        numberMap.put(ECardNumber.NUM_5, R.drawable.spade_5);
        numberMap.put(ECardNumber.NUM_6, R.drawable.spade_6);
        numberMap.put(ECardNumber.NUM_7, R.drawable.spade_7);
        numberMap.put(ECardNumber.NUM_8, R.drawable.spade_8);
        numberMap.put(ECardNumber.NUM_9, R.drawable.spade_9);
        numberMap.put(ECardNumber.NUM_10, R.drawable.spade_10);
        numberMap.put(ECardNumber.NUM_J, R.drawable.spade_j);
        numberMap.put(ECardNumber.NUM_Q, R.drawable.spade_q);
        numberMap.put(ECardNumber.NUM_K, R.drawable.spade_k);
        numberMap.put(ECardNumber.NUM_A, R.drawable.spade_a);
        numberMap.put(ECardNumber.NUM_2, R.drawable.spade_2);
    }

    public CardGameView()
    {

    }

    public CardGameView(Card pCard, ShowType pShowType)
    {
        reset(pCard, pShowType);
    }

    public void reset(Card pCard, ShowType pShowType)
    {
        mCard = pCard;
        mShowType = pShowType;
        mSelected = false;

        updateBaseView();
    }

    public Card getCard()
    {
        return mCard;
    }

    public void setCard(Card pCard)
    {
        mCard = pCard;
        updateBaseView();
    }

    public boolean isSelected()
    {
        return mSelected;
    }

    public void setSelected(boolean pSelected)
    {
        mSelected = pSelected;
    }

    public ShowType getShowType()
    {
        return mShowType;
    }

    public void setShowType(ShowType pShowType)
    {
        mShowType = pShowType;
        updateBaseView();
    }

    @Override
    public void onDraw(Canvas pCanvas)
    {
        if (mSelected)
        {
            setPosY(getPosY() - sSelectYDelta);
        }
        super.onDraw(pCanvas);

        if (mSelected)
        {
            setPosY(getPosY() + sSelectYDelta);
        }
    }

    protected void updateBaseView()
    {
        if (mCard == null)
        {
            setBitmap(null);
        }
        else
        {
            int resId = 0;
            switch (mShowType)
            {
                case BigBack:
                    resId = mBigBackResId;
                    break;
                case BigFront:
                    resId = sBigFrontResIds.get(mCard.getSuit()).get(mCard.getNumber());
                    break;
                case SmallFront:
                    resId = sSmallFrontResIds.get(mCard.getSuit()).get(mCard.getNumber());
                    break;
            }
            switch (mShowType)
            {
                case BigBack:
                case BigFront:
                    setWidth(sBigWidth);
                    setHeight(sBigHeight);
                    break;
                case SmallFront:
                    setWidth(sSmallWidth);
                    setHeight(sSmallHeight);
                    break;
            }
            if (resId != 0)
            {
                setBitmap(GameBoardView.getInstance().getBitmapFactory().getBitmap(resId));
            }
        }
    }

    public enum ShowType
    {
        BigFront,
        BigBack,
        SmallFront
    }
}
