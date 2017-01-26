package com.tutorials.hp.sqliteinfinitepager.mData;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;
import com.tutorials.hp.sqliteinfinitepager.mRecycler.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import co.uk.rushorm.core.RushSearch;

/**
 * Created by Oclemy on 1/25/2017 for ProgrammingWizards Channel and http://www.camposha.com.
 */

public class Paginator {

    Context c;
    private PullToLoadView pullToLoadView;
    RecyclerView rv;
    private MyAdapter adapter;
    private boolean isLoading = false;
    private boolean hasLoadedAll = false;
    private int nextPage;


    private int ITEMS_PER_PAGE;


   /*
   CONSTRUCTOR
    */
    public Paginator(Context c, PullToLoadView pullToLoadView,RecyclerView rv) {
        this.c = c;
        this.pullToLoadView = pullToLoadView;
        this.rv=rv;

        ITEMS_PER_PAGE=5;

        adapter = new MyAdapter(c, new ArrayList<Spaceship>());
        rv.setAdapter(adapter);

        initializePaginator();
    }

    /*
    PAGE DATA
     */
    public void initializePaginator() {
        pullToLoadView.isLoadMoreEnabled(true);
        pullToLoadView.setPullCallback(new PullCallback() {

            //LOAD MORE DATA
            @Override
            public void onLoadMore() {
                loadData(nextPage);
                Toast.makeText(c, "Load More", Toast.LENGTH_SHORT).show();
            }

            //REFRESH AND TAKE US TO FIRST PAGE
            @Override
            public void onRefresh() {
                adapter.clear();
                hasLoadedAll = false;
                loadData(0);
            }

            //IS LOADING
            @Override
            public boolean isLoading() {
                return isLoading;
            }

            //CURRENT PAGE LOADED
            @Override
            public boolean hasLoadedAllItems() {
                return hasLoadedAll;
            }
        });

        pullToLoadView.initLoad();
    }


    /*
    LOAD DATA.PASS CURRENT PAGE
     */
    public void loadData(final int page) {
        isLoading = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //ADD CURRENT PAGE TO OUR ADAPTER
                for(Spaceship spaceship : getCurrentSpacecrafts(page))
                {
                    adapter.add(spaceship);
                }
                //UPDATE PROPETIES
                pullToLoadView.setComplete();
                isLoading = false;
                nextPage = page + 1;
            }

        }, 3000);

    }


    /*
    CURRENT PAGE SPACECRAFTS LIST
     */
    public List<Spaceship> getCurrentSpacecrafts(int currentPage)
    {
        int startItem=currentPage*ITEMS_PER_PAGE;
        List<Spaceship> currentSpacecrafts=new ArrayList<>();
        try
        {
            //PAGE AT SERVER SIDE
            currentSpacecrafts=new RushSearch().limit(ITEMS_PER_PAGE).offset(startItem).find(Spaceship.class);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return currentSpacecrafts;
    }



}