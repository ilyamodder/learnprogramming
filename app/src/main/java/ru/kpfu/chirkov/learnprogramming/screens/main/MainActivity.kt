package ru.kpfu.chirkov.learnprogramming.screens.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.kpfu.chirkov.learnprogramming.R
import ru.kpfu.chirkov.learnprogramming.screens.dashboard.DashboardFragment
import ru.kpfu.chirkov.learnprogramming.screens.tasks.TasksFragment
import ru.kpfu.chirkov.learnprogramming.screens.theory_list.TheoryListFragment

class MainActivity : AppCompatActivity() {

    private val tasksFragment: Fragment by lazy { TasksFragment() };
    private val theoryListFragment: Fragment by lazy { TheoryListFragment() }
    private val dashboardFragment: Fragment by lazy { DashboardFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment

            when (item.itemId) {
                R.id.navigation_tasks -> {
                    fragment = tasksFragment
                }
                R.id.navigation_theory -> {
                    fragment = theoryListFragment
                }
                R.id.navigation_dashboard -> {
                    fragment = dashboardFragment
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.content, fragment)
            transaction.commit()
            true
        }
        navigation.selectedItemId = R.id.navigation_tasks
    }

}
