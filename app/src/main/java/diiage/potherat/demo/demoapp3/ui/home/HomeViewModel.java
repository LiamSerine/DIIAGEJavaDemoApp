package diiage.potherat.demo.demoapp3.ui.home;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.model.Quote;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mQuotesCountText;
    private MutableLiveData<String> mCountAuthorText;
    private MutableLiveData<Quote> mLastQuote;

    private QuoteRepository _quoteRepository;

    @Inject
    @ViewModelInject
    public HomeViewModel(QuoteRepository quoteRepository) {
        _quoteRepository = quoteRepository;

        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

        mQuotesCountText = new MutableLiveData<>();
        mQuotesCountText.setValue("Nombre total de citation : 0");

        mCountAuthorText = new MutableLiveData<>();
        mCountAuthorText.setValue("Nombre d'auteur distinct : 0");

        mLastQuote = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<String> getQuotesCountText(){return mQuotesCountText;}

    public LiveData<String> getCountAuthorText(){return mCountAuthorText;}

    public LiveData<Quote> getLastQuote(){return mLastQuote;}

    public void loadQuotesCount()
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
               Integer result = _quoteRepository.countQuote();
               mQuotesCountText.postValue("Nombre total de citation : " + result);
            }
        });

        thread.start();
    }

    public void loadCountAuthor()
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Integer result = _quoteRepository.getCountAuthor();
                mCountAuthorText.postValue("Nombre d'auteur distinct : " + result);
            }
        });

        thread.start();
    }

    public void loadLastQuote()
    {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Quote result = _quoteRepository.getLastQuote();
                mLastQuote.postValue(result);
            }
        });

        thread.start();
    }

}