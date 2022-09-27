package technoxcode.com.ageinminutes

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var selectedatetv: TextView? = null
    private var tvageinminutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btndatepicker: Button = findViewById(R.id.buttondatepicker)
        selectedatetv = findViewById(R.id.textViewselecteddate)
        tvageinminutes = findViewById(R.id.textViewminutes)
        btndatepicker.setOnClickListener {
            datepickerclick()

        }
    }

    private fun datepickerclick() {

        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        //DatePickerDialog()
        val dpd = DatePickerDialog(
            this, { _, Selectedyear, Selectedmonth, SelecteddayofMonth ->
                //  Toast.makeText(this, "Date picker works", Toast.LENGTH_LONG).show()
               val mytoast = Toast.makeText(
                    this,
                    "Selected Year was $Selectedyear, month was ${Selectedmonth + 1}, Day was $SelecteddayofMonth",
                    Toast.LENGTH_LONG,);
                mytoast.getView()?.setBackgroundColor((Color.parseColor("#F6AE2D")));mytoast.show()//It count month from 0 so we added 1 and made it

                val selecteddate = "$SelecteddayofMonth/${Selectedmonth + 1}/$Selectedyear"
                selectedatetv?.setText(selecteddate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val thedate = sdf.parse(selecteddate)
                thedate?.let {
                    val selectedDateinMinutes = thedate.time / 60000
                    val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val selectedDateinhrs = thedate.time/ 60000/60
                    //val currentdatehr = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentdate?.let {  //it will run code only when date is not empty
                        val currentdateinminutes = currentdate.time / 60000
                        val currentdatehr = currentdate.time/60000/60
                        val differenceInminutes = currentdateinminutes - selectedDateinMinutes
                        val differenceinHrs = currentdatehr - selectedDateinhrs
                        tvageinminutes?.text=("Hrs: $differenceinHrs hrs/ Minutes: $differenceInminutes min").toString()

                    }


                }

            },
            year,
            month,
            day

        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }
}