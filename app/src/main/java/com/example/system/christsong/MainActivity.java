package com.example.system.christsong;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import dao.DatabaseQueryClass;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   Button bangla,bangla2;
   Dialog dialog;
    GridView vowel,consonant;
    DrawerLayout drawerLayout;
     ActionBarDrawerToggle dtrogle;
    MaterialSearchView materialSearchView;
    android.support.v7.widget.Toolbar toolbar;
    String [] list=null;//={"ssjjs","lksksksk","ajajaj"};
    String [] list1={"hdhd","lkjsdkjsksksk","hahahjajaj"};
    ArrayList<String> banglatest=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog=new Dialog(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        dtrogle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(dtrogle);
        dtrogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
       //bangla2=(Button)findViewById(R.id.button2);
      // bangla=(Button)findViewById(R.id.button1);
       // RelativeLayoutButton button1 = new RelativeLayoutButton(this,R.id.button1);

        //Buttons
         LinearLayout button1;
        button1 = new LinearLayout(this);
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(this);
        //Button2
        LinearLayout button2;
        button2 = new LinearLayout(this);
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(this);
        //Button3
        LinearLayout button3;
        button3 = new LinearLayout(this);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(this);
        //Button4
        LinearLayout button4;
        button4 = new LinearLayout(this);
        button4=findViewById(R.id.button4);
        button4.setOnClickListener(this);
        //Button5
        Button button5;
        button5=findViewById(R.id.button5);
        button5.setOnClickListener(this);
        //Button6
        Button button6;
        button6=findViewById(R.id.button6);
        button6.setOnClickListener(this);
      //bangla.setText(Html.fromHtml("String I am testing<br/><span style=\"font-size: 5px ;\">Hello</span>"));
        //Typeface font= Typeface.createFromAsset(getAssets(), "kalpurush.ttf");

     //Search view
        materialSearchView=(MaterialSearchView)findViewById(R.id.search_view);
        materialSearchView.closeSearch();
         materialSearchView.setEllipsize(true);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.equals("job")){
                    Toast.makeText(getApplicationContext(),"job",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"haha",Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()) {
                    //Toast.makeText(getApplicationContext(),newText,Toast.LENGTH_SHORT).show();
                    DatabaseQueryClass db = new DatabaseQueryClass(getApplicationContext());
                    list = db.getSongBySearch(newText);
//                    Toast.makeText(getApplicationContext(),list[0],Toast.LENGTH_SHORT).show();
//                    if (newText.equals("jj")) {
//                        list[0] = "jjkএস হে করুণাময়";
//                        // list1.add("kak");
//                   }


                    materialSearchView.setSuggestions(list);
                    //click on suggetion
                    materialSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String query = (String) parent.getItemAtPosition(position);
                            DatabaseQueryClass db = new DatabaseQueryClass(getApplicationContext());
                            int number = db.getSongNumberByTitle(query);
                           Intent intent=new Intent(MainActivity.this,LyricPage.class);
                           intent.putExtra("songnumber",number);
                           intent.putExtra("actionbartitle",query);
                           startActivity(intent);
                            materialSearchView.closeSearch();
                        }
                    });
                    ////click on suggetion end
                   // list=null;
                    return true;
                }
                else{
                    //list=null;
                    materialSearchView.setSuggestions(list);
                    return true;
                }




            }
        });
//        materialSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this,"Coming sjkdjksjd", Toast.LENGTH_SHORT).show();
//            }
//        });
        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.search);
        materialSearchView.setMenuItem(item);
        return true;
    }



//    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(dtrogle.onOptionsItemSelected(item)){
            return true;
        }
        if(item.getItemId()==R.id.search){
            return true;
        }
        if(item.getItemId()==R.id.favourites){
            Intent intent=new Intent(MainActivity.this,Favourites.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button1){
//            String [] list=getResources().getStringArray(R.array.listofalphabate);
//            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//            intent.putExtra("list",list);
//            intent.putExtra("book","prosongsa");
//            startActivity(intent);
            Intent intent=new Intent(MainActivity.this,MainIndex.class);
            intent.putExtra("actionbartitle",getResources().getString(R.string.button3));
            startActivity(intent);
            Toast.makeText(MainActivity.this,"Coming soon", Toast.LENGTH_SHORT).show();

        }
        else if(view.getId()==R.id.button2){
          //  showpopup(view);
//            String [] list=getResources().getStringArray(R.array.listofalphabate2);
//            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//            intent.putExtra("list",list);
//            startActivity(intent);
            Toast.makeText(MainActivity.this,"Coming soon", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.button3){///sebok songit

//            Intent intent=new Intent(MainActivity.this,MainIndex.class);
//            intent.putExtra("actionbartitle",getResources().getString(R.string.button3));
//            startActivity(intent);
            Toast.makeText(MainActivity.this,"Coming soon....", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.button4){
            Intent intent=new Intent(MainActivity.this,About_us.class);
            startActivity(intent);

            //Toast.makeText(MainActivity.this,"Coming soon..."+banglatest, Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.button5){
//            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//            startActivity(intent);
            Toast.makeText(MainActivity.this,"Coming soon...", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId()==R.id.button6){
//            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
//            startActivity(intent);
            Toast.makeText(MainActivity.this,"Coming soon....", Toast.LENGTH_SHORT).show();
        }

    }

//    public  void showpopup(View view){
//
//
//        dialog.setContentView(R.layout.searchpopup);
//        TextView x=dialog.findViewById(R.id.cross);
//        vowel=dialog.findViewById(R.id.vowel);
//        consonant=dialog.findViewById(R.id.consonant);
//        String [] list=getResources().getStringArray(R.array.listofalphabate);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,R.layout.index_icon_view,R.id.customadaptarTextview,list);
//       vowel.setAdapter(adapter);
//        vowel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String a=(String) vowel.getItemAtPosition(i);
//                Toast.makeText(MainActivity.this,"you have been clicked:"+a,Toast.LENGTH_SHORT).show();
//            }
//        });
//        x.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//@Override
//public boolean onKeyPreIme(int keyCode, KeyEvent event) {
//    boolean retVal = false;
//
//    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//        InputMethodManager manager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
//        return retVal;
//    }
//    return super.onk(event);
//}
@Override
public void onBackPressed() {
    if (materialSearchView.isSearchOpen()) {
        materialSearchView.closeSearch();

    } else {
        super.onBackPressed();
    }
}
}
