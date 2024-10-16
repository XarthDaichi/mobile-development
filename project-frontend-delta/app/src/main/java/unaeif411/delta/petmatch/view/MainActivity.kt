package unaeif411.delta.petmatch.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import unaeif411.delta.petmatch.R
import unaeif411.delta.petmatch.databinding.ActivityMainAdminBinding
import unaeif411.delta.petmatch.databinding.ActivityMainBinding
import unaeif411.delta.petmatch.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingAdmin: ActivityMainAdminBinding
    private lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        session = SessionManager(this)
        val role = session.fetchUserRole()

        if (role?.equals("ROLE_ADMIN") ?: ("ROLE_ADMIN" == null)){
            bindingAdmin = ActivityMainAdminBinding.inflate(layoutInflater)
            setContentView(bindingAdmin.root)

            val navView: BottomNavigationView = bindingAdmin.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_main_admin)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            navView.setupWithNavController(navController)
        }else{
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)


            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            navView.setupWithNavController(navController)
        }
    }
}