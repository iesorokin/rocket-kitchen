package ru.rocket.kitchen.fragments.start


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import ru.rocket.kitchen.R
import ru.rocket.kitchen.activities.MainActivity
import ru.rocket.kitchen.model.TypeKitchen

class AuthFragment : Fragment() {

    private lateinit var password: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var typeKitchen: TypeKitchen
    private lateinit var enterButton: Button

    private lateinit var progressDialog: ProgressDialog

    private var focusView: View? = null

    private lateinit var folder: String

    private var isAuth: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_auth, container, false)

        password = v.findViewById(R.id.loginPassword)
        radioGroup = v.findViewById(R.id.loginPassword)
        enterButton = v.findViewById(R.id.logBtn)
        progressDialog = ProgressDialog(activity)


        folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            typeKitchen = when (checkedId) {
                R.id.radioButtonBakery -> TypeKitchen.BAKERY
                R.id.radioButtonCold -> TypeKitchen.COLD
                R.id.radioButtonHot -> TypeKitchen.HOT
                R.id.radioButtonPost -> TypeKitchen.POST
                R.id.radioButtonBlank -> TypeKitchen.BLANK
                R.id.radioButtonCandy -> TypeKitchen.CANDY
                else -> TypeKitchen.POST
            }
        }
        enterButton.setOnClickListener {
            val password = password.text.toString()

            if (!TextUtils.isEmpty(password)) {
                progressDialog.setTitle(getString(R.string.progressDialogLogin))
                progressDialog.setMessage(getString(R.string.progressDialogWait))
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.isIndeterminate = false
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)

                val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                enterToCook(typeKitchen, password)
            } else {
                this.password.error = "Введите пароль"
                focusView = this.password
                focusView?.requestFocus()
            }
        }
        return v
    }

    private fun enterToCook(typeKitchen: TypeKitchen, password: String) {
        DownloadTask()
            .execute(
                typeKitchen.name, password
            )
    }

    private inner class DownloadTask : AsyncTask<String, Int, Void>() {

        override fun onPreExecute() {
            progressDialog.show()
        }

        override fun doInBackground(vararg tasks: String): Void? {
            isAuth = isAuthUser(tasks[0], tasks[1])
            return null
        }

        private fun isAuthUser(typeKitchen: String, password: String): Boolean {
            return true
            TODO("add shared pref for save data about auth")
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressDialog.progress = values[0]!!
        }

        override fun onPostExecute(result: Void) {
            if (!isAuth) {
                progressDialog.dismiss()
                Toast.makeText(activity, "Пароль введен неверно", Toast.LENGTH_SHORT).show()
                password.error = "Проверьте пароль"
                focusView = password
                focusView?.requestFocus()
            } else {
                progressDialog.dismiss()
                Toast.makeText(activity, "Вы на кухне", Toast.LENGTH_SHORT).show()
                sentToMainActivity()
            }
        }
    }

    private fun sentToMainActivity() {
        val mainIntent = Intent(activity, MainActivity::class.java)
        startActivity(mainIntent)
        activity?.finish()
    }

}
