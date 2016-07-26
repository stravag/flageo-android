package ch.ranil.android.flageo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static ch.ranil.android.flageo.model.Flag.*;

public class FlagQuizBuilder {

    protected static List<Set<Flag>> similarFlags;
    static {
        similarFlags = new ArrayList<>();
        // red white black
        similarFlags.add(new HashSet<>(Arrays.asList(EGYPT, SYRIA, IRAQ, YEMEN, SUDAN, JORDAN, KUWAIT,
                                                     UNITED_ARAB_EMIRATES, WESTERN_SAHARA)));
        // red blue yellow
        similarFlags.add(new HashSet<>(Arrays.asList(ROMANIA, CHAD, MOLDOVA, ANDORRA)));
        // red yellow
        similarFlags.add(new HashSet<>(Arrays.asList(PEOPLES_REPUBLIC_OF_CHINA, KYRGYZSTAN, MACEDONIA,
                                                     MONGOLIA)));
        // red white
        similarFlags.add(new HashSet<>(Arrays.asList(AUSTRIA, LATVIA, LEBANON, INDONESIA, MONACO, POLAND,
                                                     JAPAN, QATAR, BAHRAIN, SINGAPORE, MALTA, CANADA,
                                                     GEORGIA, PERU)));
        // red white blue
        similarFlags.add(new HashSet<>(Arrays.asList(RUSSIA, SERBIA, SLOVAKIA, SLOVENIA, NETHERLANDS,
                                                     LUXEMBOURG, CROATIA, PARAGUAY, THAILAND, COSTA_RICA,
                                                     NORTH_KOREA, CUBA, NORTH_KOREA, CAMBODIA, CHILE,
                                                     UNITED_STATES, LIBERIA, DOMINICAN_REPUBLIC, FRANCE,
                                                     KIRIBATI, LAOS, MALAYSIA, NEPAL, PANAMA)));
        // cross
        similarFlags.add(new HashSet<>(Arrays.asList(SWITZERLAND, TONGA)));
        // red green black
        similarFlags.add(new HashSet<>(Arrays.asList(MALAWI, ZAMBIA, AFGHANISTAN, LIBYA)));
        // circles
        similarFlags.add(new HashSet<>(Arrays.asList(JAPAN, BANGLADESH, PALAU)));
        // half-moon
        similarFlags.add(new HashSet<>(Arrays.asList(ALGERIA, TUNISIA, MAURITANIA, PAKISTAN, COMOROS,
                                                     MALAYSIA, MALDIVES, SAUDI_ARABIA, TURKEY, TURKMENISTAN)));
        // green yellow red
        similarFlags.add(new HashSet<>(Arrays.asList(MALI, GUINEA, CAMEROON, SENEGAL, BENIN, GUINEABISSAU,
                                                     GHANA, REPUBLIC_OF_THE_CONGO, SAO_TOME_PRINCIPE,
                                                     TOGO)));
        // blue white (black)
        similarFlags.add(new HashSet<>(Arrays.asList(ARGENTINA, ISRAEL, EL_SALVADOR, NICARAGUA, HONDURAS,
                                                     GUATEMALA, BOTSWANA, ESTONIA, GREECE, URUGUAY)));
        // diagonal
        similarFlags.add(new HashSet<>(Arrays.asList(TANZANIA, SAINT_KITTS_NEVIS, TRINIDAD_TOBAGO,
                                                     DEMOCRATIC_REPUBLIC_OF_THE_CONGO, BRUNEI,
                                                     JAMAICA, PAPUA_NEW_GUINEA, BHUTAN, GUYANA,
                                                     NAMIBIA, REPUBLIC_OF_THE_CONGO, SEYCHELLES)));
        // nordic cross
        similarFlags.add(new HashSet<>(Arrays.asList(ICELAND, FINLAND, NORWAY, SWEDEN, DENMARK, GEORGIA)));
        // other similarities
        similarFlags.add(new HashSet<>(Arrays.asList(ANGOLA, PAPUA_NEW_GUINEA, ANTIGUA_BARBUDA,
                                                     MONTENEGRO, TRINIDAD_TOBAGO)));
        // stars
        similarFlags.add(new HashSet<>(Arrays.asList(BOSNIA_HERZEGOVINA, MARSHALL_ISLANDS, SOLOMON_ISLANDS,
                                                     CAPE_VERDE, DJIBOUTI, KOSOVO, MICRONESIA, NAURU)));
        // colorful
        similarFlags.add(new HashSet<>(Arrays.asList(BELIZE, DOMINICA, SOUTH_AFRICA, VANUATU, SAINT_KITTS_NEVIS,
                                                     CENTRAL_AFRICAN_REPUBLIC, GAMBIA, KENYA, ERITREA,
                                                     GRENADA, GUYANA, MOZAMBIQUE, SRI_LANKA, SWAZILAND, ZIMBABWE)));
        // union jack
        similarFlags.add(new HashSet<>(Arrays.asList(NEW_ZEALAND, AUSTRALIA, UNITED_KINGDOM, FIJI,
                                                     TUVALU)));
        // red white green
        similarFlags.add(new HashSet<>(Arrays.asList(COTE_DIVOIRE, IRELAND, ITALY, MEXICO, INDIA, NIGER,
                                                     EQUATORIAL_GUINEA, GUYANA, NIGERIA)));
        // blue yellow
        similarFlags.add(new HashSet<>(Arrays.asList(PALAU, KAZAKHSTAN, SAINT_LUCIA)));
        similarFlags.add(new HashSet<>(Arrays.asList(BELARUS, PORTUGAL, BRAZIL, BURUNDI, SOUTH_KOREA)));
        similarFlags.add(new HashSet<>(Arrays.asList(LITHUANIA, MYANMAR, BURKINA_FASO, MOROCCO, SOMALIA,
                                                     SURINAME, VIETNAM)));
        similarFlags.add(new HashSet<>(Arrays.asList(CZECH_REPUBLIC, PHILIPPINES, REPUBLIC_OF_CHINA, SAMOA)));
        similarFlags.add(new HashSet<>(Arrays.asList(SIERRA_LEONE, LESOTHO, CYPRUS)));
        similarFlags.add(new HashSet<>(Arrays.asList(COLOMBIA, VENEZUELA, ECUADOR, MAURITIUS, ARMENIA)));
        similarFlags.add(new HashSet<>(Arrays.asList(RWANDA, GABON, UKRAINE, SAINT_VINCENT_THE_GRENADINES)));
        similarFlags.add(new HashSet<>(Arrays.asList(LIECHTENSTEIN, HAITI)));
        similarFlags.add(new HashSet<>(Arrays.asList(LIBYA, AZERBAIJAN, UZBEKISTAN)));
        similarFlags.add(new HashSet<>(Arrays.asList(BAHAMAS, BARBADOS)));
        similarFlags.add(new HashSet<>(Arrays.asList(SAN_MARINO, VATICAN_CITY)));
        similarFlags.add(new HashSet<>(Arrays.asList(ETHIOPIA, BOLIVIA)));
        similarFlags.add(new HashSet<>(Arrays.asList(MADAGASCAR, OMAN, BULGARIA)));
        similarFlags.add(new HashSet<>(Arrays.asList(IRAN, TAJIKISTAN, HUNGARY, AFGHANISTAN)));
        similarFlags.add(new HashSet<>(Arrays.asList(ALBANIA, MONTENEGRO)));
        similarFlags.add(new HashSet<>(Arrays.asList(GERMANY, BELGIUM, SPAIN, UGANDA)));
    }

    private static FlagQuizBuilder instance;
    private List<Flag> unasked;

    public static FlagQuizBuilder getInstance() {
        if (instance == null) {
            throw new IllegalStateException("FlagQuizBuilder not initialized, make sure you create an instance first.");
        }
        return instance;
    }

    public static FlagQuizBuilder newInstance() {
        instance = new FlagQuizBuilder();
        return instance;
    }

    private FlagQuizBuilder() {
        unasked = new ArrayList<>();
        unasked.addAll(getQuizables());
    }

    private List<Flag> getQuizables() {
        List<Flag> quizables = new ArrayList<>();
        Collections.addAll(quizables, Flag.values());
        return quizables;
    }

    /**
     * Gets a random unasked flag.
     *
     * @return flag
     * @throws NothingToQuizException
     */
    public Flag nextUnasked() throws NothingToQuizException {
        if (unasked.isEmpty()) {
            throw new NothingToQuizException();
        }

        Random r = ThreadLocalRandom.current();
        int index = r.nextInt(unasked.size());
        index = index == unasked.size() ? index - 1 : index;

        return unasked.remove(index);
    }

    /**
     * Creates a random quiz of unasked flags.
     *
     * @param size number of options
     * @return quiz
     * @throws NothingToQuizException
     */
    public Quiz<Flag> buildQuiz(int size, Difficulty difficulty) throws NothingToQuizException {

        Flag answer = nextUnasked();
        Random r = ThreadLocalRandom.current();

        List<Flag> quizables = getQuizables();

        Flag[] options = new Flag[size];
        quizables.remove(answer);
        options[0] = answer;

        int i = 1;
        i += fillWithSimilarOptions(answer, difficulty, options, i);
        for (; i < options.length; i++) {
            options[i] = quizables.remove(r.nextInt(quizables.size() - 1));
        }

        shuffleArray(options);

        return new Quiz<>(options, answer);
    }

    /**
     * Fill options with similar flags.
     *
     * @param flag answer flag
     * @param options flags to fill
     * @return number of similar flags found and added
     */
    private int fillWithSimilarOptions(Flag flag, Difficulty difficulty, Flag[] options, int index) {
        int filled = 0;
        Set<Flag> similarFlags = getSimilarFlags(flag);
        if (!similarFlags.isEmpty()) {
            Random r = new Random();
            List<Flag> tmp = new ArrayList<>(similarFlags);
            for (int i = index; i <= difficulty.getSimilars() && i < options.length && !tmp.isEmpty(); i++) {
                int rand = r.nextInt(tmp.size());
                options[i] = tmp.remove(rand);
                filled++;
            }
        }
        return filled;
    }

    protected static Set<Flag> getSimilarFlags(Flag flag) {
        Set<Flag> similar = new HashSet<>();
        for (Set<Flag> set : similarFlags) {
            if (set.contains(flag)) {
                similar.addAll(set);
            }
        }
        similar.remove(flag);
        return similar;
    }

    // Implementing Fisher–Yates shuffle
    private static <T> void shuffleArray(T[] ar) {

        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            T a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public class NothingToQuizException extends Exception {
    }
}
