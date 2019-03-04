package com.appkamus.submission.appkamus.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appkamus.submission.appkamus.R;
import com.appkamus.submission.appkamus.model.Kamus;
import com.appkamus.submission.appkamus.presenter.KamusPresenter;
import com.appkamus.submission.appkamus.view.activity.DetailActivity;
import com.appkamus.submission.appkamus.view.activity.MainActivity;
import com.appkamus.submission.appkamus.view.adapter.KamusAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Dicoding Academy
 *
 * Submisison 3 Aplikasi Kamus
 * Menjadi Developer Expert (MADE)
 *
 * Created by kheys on 13/01/19.
 */
public class BahasaToEnglishFragment extends BaseFragment implements KamusView{
    public static final String TAG = BahasaToEnglishFragment.class.getSimpleName();

    @BindView(R.id.rv_kamus)
    protected RecyclerView rv_kamus;

    private KamusAdapter mKamusAdapter;

    public BahasaToEnglishFragment() {}

    public static BahasaToEnglishFragment newInstance() {
        BahasaToEnglishFragment fragment = new BahasaToEnglishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initial Presenter
        KamusPresenter kamusPresenter = new KamusPresenter(this);

        // Setup View
        setupToolbar();
        setupRecyclerview();

        kamusPresenter.loadDataIndoToEnglish();


    }

    @Override
    public void setupRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        if(mKamusAdapter !=null && mKamusAdapter.getItemCount() > 0){
            mKamusAdapter.clearList();
        }

        mKamusAdapter = new KamusAdapter(this);

        rv_kamus.setLayoutManager(linearLayoutManager);
        rv_kamus.setAdapter(mKamusAdapter);

    }

    @Override
    public void setupToolbar() {
        if(getActivity() !=null){
            ((MainActivity)getActivity()).changeTitleBar(getString(R.string.kamus_title));
            ((MainActivity)getActivity()).changeSubTitleBar(getString(R.string.nav_indonesia_to_english_title));
            ((MainActivity)getActivity()).setTvSubToolbarDesc(getString(R.string.nav_indonesia_to_english_title));
        }
    }

    @Override
    public void loadData(List<Kamus> kamus) {
        mKamusAdapter.addList(kamus);
    }

    @Override
    public void goToActivity(Kamus kamus) {
        Intent intent = new Intent(getActivity(),DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TITLE, kamus.getword());
        intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, kamus.getContent());
        startActivity(intent);
    }

    @Override
    public void searchKamus(String text) {
        mKamusAdapter.getFilter().filter(text);
    }
}
