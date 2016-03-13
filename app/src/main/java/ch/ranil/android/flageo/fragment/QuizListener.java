package ch.ranil.android.flageo.fragment;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface QuizListener {

    /**
     * Method is triggered when a quiz is answered.
     *
     * @param correct true if answer is correct, false if not
     */
    void quizAnswered(boolean correct);

    /**
     * Boost the timer by the given time, can also be negative.
     *
     * @param addedTime time to add to timer
     */
    void timeBoost(long addedTime);

    /**
     * In the unlikely event that every flag has been answered.
     */
    void answeredAllQuestions();
}