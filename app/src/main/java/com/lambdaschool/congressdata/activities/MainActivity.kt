package com.lambdaschool.congressdata.activities


import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.congressdata.R
import com.lambdaschool.congressdata.importedjava.CongressDao.getAllMembers
import com.lambdaschool.congressdata.importedjava.CongresspersonOverview
import com.lambdaschool.congressdata.model.OfficialOverview
import com.lambdaschool.congressdata.viewmodel.CongresspersonListViewModel
import com.lambdaschool.congressdata.viewmodel.OverviewListAdapter
import com.lambdaschool.congressdata.viewmodel.themeUtils
import kotlinx.android.synthetic.main.activity_main.*


/*public class MainActivity extends LifecycleActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        model.getFruitList().observe(this, fruitlist -> {
            // update UI
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });
    }
}*/


class MainActivity : AppCompatActivity() {

    private lateinit var layoutList: RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var listAdapter: OverviewListAdapter

    private var context: Context? = null
    private lateinit var viewModel: CongresspersonListViewModel

    var themeId: Int = 0
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        themeUtils.onActivityCreateSetTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        layoutList = layout_list
        viewModel = ViewModelProviders.of(this).get(CongresspersonListViewModel::class.java)
        setupRecyclerView()
        btn_test.setOnClickListener {  }
        updateRecyclerView(layoutList.adapter as OverviewListAdapter,getAllMembers() as ArrayList<OfficialOverview>)
    }

    override fun setTheme(themeId: Int) {
        super.setTheme(themeId)
        this.themeId = themeId
    }


    override fun onStart() {
        if (themeId != themeUtils.getcTheme(this)) {
            themeUtils.refreshActivity(this)
        }
        super.onStart()
    }

    override fun onResume() {
        if (themeId != themeUtils.getcTheme(this)) {
            themeUtils.refreshActivity(this)
        }
        super.onResume()
    }

    /**
     * This method generates default TextView objects for the congressperson list in this activity.
     *
     * @param text display name for the congressperson
     * @param id   api id for the congressperson
     * @return TextView object with the text and tag set as provided
     */
    private fun getDefaultTextView(text: String, id: String): TextView {
        val dataView = TextView(context)
        dataView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24f)
        dataView.setPadding(10, 20, 10, 20)
        dataView.typeface = Typeface.DEFAULT_BOLD
        dataView.text = text
        dataView.tag = id

        dataView.setOnClickListener { view ->
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", view.tag.toString())
            startActivity(intent)
        }
        return dataView
    }

    fun setupRecyclerView() {

        layoutList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        layoutList.layoutManager = layoutManager
        listAdapter = OverviewListAdapter()
        layoutList.adapter = listAdapter
        viewModel.overviewList?.observe(this, Observer {

                it?.let {
                    updateRecyclerView(listAdapter,it)
                }
        })


    }


    fun updateRecyclerView(adapter: OverviewListAdapter,congoList:ArrayList<OfficialOverview>) {
        adapter.submitList(congoList as ArrayList<OfficialOverview>)
        adapter.notifyDataSetChanged()
    }


}
