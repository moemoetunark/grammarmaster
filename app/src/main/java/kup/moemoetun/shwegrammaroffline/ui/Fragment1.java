package kup.moemoetun.shwegrammaroffline.ui;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kup.moemoetun.shwegrammaroffline.R;
import kup.moemoetun.shwegrammaroffline.adapter.MyRecyclerViewAdapter;
import kup.moemoetun.shwegrammaroffline.quiz.QuizMain;
public class Fragment1 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    private InterstitialAd mInterstitialAd;
    MyRecyclerViewAdapter adapter;
    private AdRequest adRequest;
    ArrayList<String> animalNames = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_level1, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        animalNames.add("အခန်း (၁) am/is/are ကိုသုံးပုံ");
        animalNames.add("အခန်း (၂) am/is/are သုံးပုံ ၂");
        animalNames.add("Present Continuous -(I'm doing)-ပစ္စုပ္ပန်ဆက်လက်ကါလ");
        animalNames.add("Present Continuous-(Are you doing..?)-ပစ္စုပ္ပန်ဆက်လက်ကါလမေးခွန်း");
        animalNames.add("Present Simple -(I do/she does)ရိုးရိုးပစ္စုပ္ပန်ကါလ");
        animalNames.add("Present Simple Negative-(I don't/she doesn't");
        animalNames.add("Present Simple Question- ရိုးရိုးပစ္စပ္ပန်ကါလမေးခွန်း");
        animalNames.add("Present Simple and Continuous");
        animalNames.add("Have got/has got တို့ကို အသုံးပြုပုံစံများ");
        animalNames.add("Was,Were-ရှိခဲ့သည်၊ဖြစ်ခဲ့သည်");

        animalNames.add("Past Simple (I did) - ရိုးရိုးအတိတ်ကါလ");
        animalNames.add("past Simple မေးခွန်း (Did you do...?");
        animalNames.add("Past Continuous-အတိတ်ဆက်လက်ကါလ (I was doing)");
        animalNames.add("Past Simple and Past Continuous - ကိုသုံးပုံများ");
        animalNames.add("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၁)");
        animalNames.add("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၂)");
        animalNames.add("Present Perfect-ပစ္စပ္ပန်ပြီးစီး (I've done) သုံးပုံ (၃)");
        animalNames.add("For ကြာကြာ/Since ကတည်းက/ago စသည်တို့ကိုကိုသုံးပုံများ");
        animalNames.add("Present Perfect နှင့် Past Simple ကို မှန်ကန်အောင်သုံးပုံများ");
        animalNames.add("Passive Voice သုံးပုံသုံးနည်းများ");
        animalNames.add("Present Perfect မှာ passive voice သုံးပုံများ");
        animalNames.add("Present Perfect နှင့် Past Simple သုံးပုံသုံးနည်း (၂) ");
        animalNames.add("Used to + V1. .....လေ့ရှိခဲ့တယ်");
        animalNames.add("What are you doing tomorrow? \n Present Continuous ကို Future ပုံစံဖြင့် သုံးပုံ");
        animalNames.add("Going to + V1. ...တော့မယ်");
        animalNames.add("Modal Verb -Will/Shill ကိုသုံးပုံ သုံးနည်း");
        animalNames.add("Modal Verb-Will/Shill ကိုသုံးပုံ သုံးနည်း");
        animalNames.add("Modal Verb - might ကိုသုံးပုံစံများ");
        animalNames.add("Modal Verb - Can/Could ကိုသုံးပုံစံများ");
        animalNames.add("Modal Verb -Must, Mustn't, Needn't အသုံးပြုပုံများ");
        animalNames.add("Modal Verb- Should - သင့်တယ်");
        animalNames.add("Have to + V1. ...ရမယ်");
        animalNames.add("Would you like..? ");
        animalNames.add("There is, are...ပစ္စုပ္ပန်ကါလအသုံးပြုပုံများ");
        animalNames.add("There was/were -အတိတ်ကါလ အသုံးပြုံပုံများ");
        animalNames.add("It - ကိုအသုံးပြုံ ပုံစံများ");
        animalNames.add("am/is/are ပြန်လည် Review လုပ်ခြင်း");
        animalNames.add("Have you....? Are you....? မေးခွန်းပုံစံများ");
        animalNames.add("So do I. So am I. စသည့်အသုံးပြုံပုံများ");
        animalNames.add("Who saw you?");
        animalNames.add("Who is she talking to?");
        animalNames.add("how, what, which -wh မေးခွန်း");
        animalNames.add("How long does it take? ဘယ်လောက်ကြာလဲ..");
        animalNames.add("Clause - ကလေ့ာများ");
        animalNames.add("She said that (Reported Speech)");
        animalNames.add("V1+ to/V1+ing  ကိုသုံးပုံများ");
        animalNames.add("I want you to ...စေချင်တယ်");
        animalNames.add("I went to the shop.... ");
        animalNames.add( "go to .....");
        animalNames.add("Get ကိုသုံးပုံစံများ");
        animalNames.add("Make and do စကားများကို သုံးနည်း");
        animalNames.add("Have and Have got ကိုသုံးနည်း");
        animalNames.add("Subject Pronoun & Object Pronoun \nကတ္တားနာမ်စား ကံနာမ်စား");
        animalNames.add("Adjective Pronoun နာမဝိသေသနကဲ့သို့ဆောင်ရွက်သော..နာမ်စား");
        animalNames.add("Possessive Pronouns -ပိုင်ဆိုင်မှုပြ နာမ်စား");
        animalNames.add("I, me, my, mine -ကိုသုံးပုံများ");
        animalNames.add("Myself, yourself, herself တို့ကိုသုံးပုံများ");
        animalNames.add(" 's - အပေါ်ထရော်ဖီ 's ကိုသုံးနည်း");
        animalNames.add("a & an ကိုသုံးနည်း");
        animalNames.add("Singular and Plural အနည်း..အများခွဲခြားခြင်း");
        animalNames.add("Countable and Uncountable Nouns -ရေတွက်ရနာမ်..ရေတွက်မရနာမ်");
        animalNames.add("A, An, Some စသည်ကို သုံးပုံ");
        animalNames.add("A, An, The စသည်ကိုသုံးပုံစံများ");
        animalNames.add("The ကိုသုံးပုံစံများ");
        animalNames.add("go and the ကိုမှန်အောင် သုံးပုံ");

        animalNames.add("The မသုံးရပုံစံများ");
        animalNames.add("နေရာများပြပုဒ်နှင့် The သုံးပုံစံများ");
        animalNames.add("This, That, Those and These စသည်ကိုသုံးပုံ");
        animalNames.add("One and Ones သုံးပုံ");
        animalNames.add("Some/any စသည်သုံးပုံစံများ");
        animalNames.add("Not one, not ..any သုံးပုံစံများ");
        animalNames.add("not anybody, anyone စသည်သုံးပုံ");
        animalNames.add("somebody/anybody စသည်သုံးပုံ");


        adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(requireContext(), getString(R.string.offfline_interstitials),
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                    }
                });


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyRecyclerViewAdapter(getContext(), animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

        if (mInterstitialAd !=null) {
            mInterstitialAd.show(requireActivity());
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
                public void onAdDismissedFullScreenContent() {
                    Intent intent = new Intent(requireContext(), QuizMain.class);
                    String clickedItemName = animalNames.get(position);
                    intent.putExtra("selectedCategory", clickedItemName);
                    startActivity(intent);
                    // Called when fullscreen content is dismissed.
                }
                @Override
                public void onAdShowedFullScreenContent() {
                    // Called when fullscreen content is shown.
                    // Make sure to set your reference to null so you don't
                    // show it a second time.
                    mInterstitialAd = null;
                }
            });

        }

        else {
            Intent intent = new Intent(requireContext(), QuizMain.class);
            String clickedItemName = animalNames.get(position);
            intent.putExtra("selectedCategory", clickedItemName);
            startActivity(intent);
        }
    }
}
