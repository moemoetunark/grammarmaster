package kup.moemoetun.shwegrammaroffline.quiz.frag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import kup.moemoetun.shwegrammaroffline.R;

public class ContentFragment extends Fragment {
    private static final String ARG_SELECTED_CATEGORY = "selectedCategory";

    private String selectedCategory;
    private Map<String, String> categoryHtmlMapping;
    private WebView webView;

    public static ContentFragment newInstance(String selectedCategory) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SELECTED_CATEGORY, selectedCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the selected category from the arguments bundle
        if (getArguments() != null) {
            selectedCategory = getArguments().getString(ARG_SELECTED_CATEGORY);
        }
        // Initialize the category to HTML file mapping
        initializeCategoryHtmlMapping();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_tab, container, false);

        webView = view.findViewById(R.id.webView);

        if (selectedCategory != null && categoryHtmlMapping.containsKey(selectedCategory)) {
            String htmlFileName = categoryHtmlMapping.get(selectedCategory);
            if (htmlFileName != null) {
                String htmlFilePath = "file:///android_asset/player/" + htmlFileName;
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setSupportZoom(true);
                webView.loadUrl(htmlFilePath);
            }
        }

        return view;
    }

    private void initializeCategoryHtmlMapping() {
        categoryHtmlMapping = new HashMap<>();
        categoryHtmlMapping.put("အခန်း (၁) am/is/are ကိုသုံးပုံ", "unit_1_am_is_are.html");
        categoryHtmlMapping.put("အခန်း (၂) am/is/are သုံးပုံ ၂", "unit_2_am_is_are_question.html");
        categoryHtmlMapping.put("Present Continuous -(I'm doing)-ပစ္စုပ္ပန်ဆက်လက်ကါလ", "unit_3_present_continuous.html");
        categoryHtmlMapping.put("Present Continuous-(Are you doing..?)-ပစ္စုပ္ပန်ဆက်လက်ကါလမေးခွန်း", "unit_4_present_continuous_question.html");
        categoryHtmlMapping.put("Present Simple -(I do/she does)ရိုးရိုးပစ္စုပ္ပန်ကါလ", "unit_5_present_simple.html");
        categoryHtmlMapping.put("Present Simple Negative-(I don't/she doesn't", "unit_6_present_simple_negative.html");
        categoryHtmlMapping.put("Present Simple Question- ရိုးရိုးပစ္စပ္ပန်ကါလမေးခွန်း", "unit_7_present_simple_question.html");
        categoryHtmlMapping.put("Present Simple and Continuous","unit_8_present_continuous_and_present_simple.html");
        categoryHtmlMapping.put("Have got/has got တို့ကို အသုံးပြုပုံစံများ","unit_9_i_have_got.html");
        categoryHtmlMapping.put("Was, Were-ရှိခဲ့သည်၊ဖြစ်ခဲ့သည်","unit_10_was_were.html");
        categoryHtmlMapping.put("Past Simple (I did) - ရိုးရိုးအတိတ်ကါလ","unit_11_past_Simple.html");
        categoryHtmlMapping.put("past Simple မေးခွန်း (Did you do...?","unit_12_past_simple.html");
        categoryHtmlMapping.put("Past Continuous-အတိတ်ဆက်လက်ကါလ (I was doing)","unit_13_I_was_doing_past_continuous.html");
        categoryHtmlMapping.put("Past Simple and Past Continuous - ကိုသုံးပုံများ","unit_14_past_simple_past_continuous.html");
        categoryHtmlMapping.put("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၁)","unit_15_present_perfect.html");
        categoryHtmlMapping.put("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၂)","unit_16_present_perfect_2.html");
        categoryHtmlMapping.put("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၃)","unit_17_present_perfect_3.html");
        categoryHtmlMapping.put("For ကြာကြာ/Since ကတည်းက/ago စသည်တို့ကိုကိုသုံးပုံများ","unit_19_for_since_ago.html");
        categoryHtmlMapping.put("Present Perfect နှင့် Past Simple ကို မှန်ကန်အောင်သုံးပုံများ","unit_20_present_perfect_n_past_simple.html");
        categoryHtmlMapping.put("Passive Voice သုံးပုံသုံးနည်းများ","unit_21_passive_voice.html");
        categoryHtmlMapping.put("Present Perfect မှာ passive voice သုံးပုံများ","unit_22_passive_voice_present_perfect.html");
        categoryHtmlMapping.put("Present Perfect နှင့် Past Simple သုံးပုံသုံးနည်း (၂)","unit_23_present_perfect_n_past.html");
        categoryHtmlMapping.put("Used to + V1. .....လေ့ရှိခဲ့တယ်","unit_24_used_to.html");
        categoryHtmlMapping.put("(What are you doing tomorrow?) --Present Continuous ကို Future ပုံစံဖြင့် သုံးပုံ","unit_26_what_are_you_doing_tomorrow.html");
        categoryHtmlMapping.put("Going to + V1. ...တော့မယ်","unit_27_going_to.html");
        categoryHtmlMapping.put("Modal Verb -Will/Shill ကိုသုံးပုံ သုံးနည်း","unit_28_will_shall.html");
        categoryHtmlMapping.put("Modal Verb-Will/Shill ကိုသုံးပုံ သုံးနည်း","unit_29_will_shall_2.html");
        categoryHtmlMapping.put("Modal Verb - might ကိုသုံးပုံစံများ","unit_30_might.html");
        categoryHtmlMapping.put("Modal Verb - Can/Could ကိုသုံးပုံစံများ","unit_31_can_could.html");
        categoryHtmlMapping.put("Modal Verb -Must, Mustn't, Needn't အသုံးပြုပုံများ","unit_32_must_musn't_needn't.html");
        categoryHtmlMapping.put("Modal Verb- Should - သင့်တယ်","unit_33_should.html");
        categoryHtmlMapping.put("Have to + V1. ...ရမယ်","unit_34_have_to.html");
        categoryHtmlMapping.put("Would you like..?","unit_35_would_you_like.html");
        categoryHtmlMapping.put("There is, are...ပစ္စုပ္ပန်ကါလအသုံးပြုပုံများ","unit_36_thiere_is_are.html");
        categoryHtmlMapping.put("There was/were -အတိတ်ကါလ အသုံးပြုံပုံများ","unit_37_there_was_were.html");
        categoryHtmlMapping.put("It - ကိုအသုံးပြုံ ပုံစံများ","unit_38_it.html");
        categoryHtmlMapping.put("am/is/are ပြန်လည် Review လုပ်ခြင်း","unit_39_am_is_are_review.html");
        categoryHtmlMapping.put("Have you....? Are you....? မေးခွန်းပုံစံများ","unit_40_have_you_are_you.html");
        categoryHtmlMapping.put("So do I. So am I. စသည့်အသုံးပြုံပုံများ","unit_41_so_do_i_so_am_i.html");
        categoryHtmlMapping.put("Who saw you?","unit_44_who_saw_you.html");
        categoryHtmlMapping.put("Who is she talking to?","unit_45_who_is_she-talking_to.html");
        categoryHtmlMapping.put("how, what, which -wh မေးခွန်း","unit_46_how_what_which.html");
        categoryHtmlMapping.put("How long does it take? ဘယ်လောက်ကြာလဲ..","unit_47_how_long_does_it_take.html");
        categoryHtmlMapping.put("Clause - ကလေ့ာများ","unit_48_clause_as_an_object.htm");
        categoryHtmlMapping.put("She said that (Reported Speech)","unit_49_she_said_that_reported_speech.html");
        categoryHtmlMapping.put("V1+ to/V1+ing  ကိုသုံးပုံများ","unit_51_verb_n_to_ing.html");
        categoryHtmlMapping.put("I want you to ...စေချင်တယ်","unit_52_I_want_you_to.html");
        categoryHtmlMapping.put("I went to the shop.... ","unit_53_i_went_to_the_shop_to.html");
        categoryHtmlMapping.put( "go to .....","unit_54_gon_to.html");
        categoryHtmlMapping.put("Get ကိုသုံးပုံစံများ","unit_55_get.html");
        categoryHtmlMapping.put("Make and do စကားများကို သုံးနည်း","unit_56_make_n_do.html");
        categoryHtmlMapping.put("Have and Have got ကိုသုံးနည်း","unit_57_have_have_got.html");
        categoryHtmlMapping.put("Subject Pronoun & Object Pronoun-ကတ္တားနာမ်စား ကံနာမ်စား","unit_58_subject_n_object_pronouns.html");
        categoryHtmlMapping.put("Adjective Pronoun နာမဝိသေသနကဲ့သို့ဆောင်ရွက်သော..နာမ်စား","unit_59_my_his_her_his.htm");
        categoryHtmlMapping.put("Possessive Pronouns -ပိုင်ဆိုင်မှုပြ နာမ်စား","unit_60_mine_hers_ours_yours.html");
        categoryHtmlMapping.put("I, me, my, mine -ကိုသုံးပုံများ","unit_61_I_me_my_mine.html");
        categoryHtmlMapping.put("Myself, yourself, herself တို့ကိုသုံးပုံများ","unit_62_myself_yourself_etc.html");
        categoryHtmlMapping.put(" 's - အပေါ်ထရော်ဖီ 's ကိုသုံးနည်း","unit_63_apotrophy_s.html");
        categoryHtmlMapping.put("a & an ကိုသုံးနည်း","unit_64_a_an.html");
        categoryHtmlMapping.put("Singular and Plural အနည်း..အများခွဲခြားခြင်း","unit_65_singular_and_plural.html");
        categoryHtmlMapping.put("Countable and Uncountable Nouns -ရေတွက်ရနာမ်..ရေတွက်မရနာမ်","unit_66_countable_and_uncountable_nouns.html");
        categoryHtmlMapping.put("A, An, Some စသည်ကို သုံးပုံ","unit_67_a_car_some_cars.html");
        categoryHtmlMapping.put("A, An, The စသည်ကိုသုံးပုံစံများ","unit_68_a_an_the.html");
        categoryHtmlMapping.put("The ကိုသုံးပုံစံများ","unit_69_the_usage.html");
        categoryHtmlMapping.put("go and the ကိုမှန်အောင် သုံးပုံ","unit_70_go_n_the.html");

        categoryHtmlMapping.put("The မသုံးရပုံစံများ","unit_71_i_like_music_i_hate_exam.html");
        categoryHtmlMapping.put("နေရာများပြပုဒ်နှင့် The သုံးပုံစံများ","unit_72_the_n_places.html");
        categoryHtmlMapping.put("This, That, Those and These စသည်ကိုသုံးပုံ","unit_73_this_that_those.html");
        categoryHtmlMapping.put("One and Ones သုံးပုံ","unit_74_one_ones.html");
        categoryHtmlMapping.put("Some/any စသည်သုံးပုံစံများ","unit_75_some_n_any.html");
        categoryHtmlMapping.put("Not one, not ..any သုံးပုံစံများ","unit_76_no_one_not_any.htm");
        categoryHtmlMapping.put("not anybody, anyone စသည်သုံးပုံ","unit_77_not_anybody_anyone.htm");
        categoryHtmlMapping.put("somebody/anybody စသည်သုံးပုံ","unit_78_somebody_anybody.htm");
        
        
        // Add more category HTML mappings as needed
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Destroy the WebView to release its resources
        if (webView != null) {
            webView.stopLoading();
            webView.clearCache(true);
            webView.clearHistory();
            webView.destroy();
        }
    }


}
