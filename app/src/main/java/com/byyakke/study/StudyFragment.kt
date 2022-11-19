package com.byyakke.study

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byyakke.study.databinding.FragmentStudyBinding
import com.byyakke.study.databinding.StudyItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StudyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudyFragment : Fragment() {

    private lateinit var studyItemViewModel: StudyItemViewModel
    private var _binding: FragmentStudyBinding? = null
    private val binding get() = _binding
    private lateinit var studyItemAdapter: StudyItemAdapter
    private lateinit var studyItem: StudyItem

    private var hasLoadedLit = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment`
        _binding = FragmentStudyBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studyItemViewModel =
            ViewModelProvider(this).get(StudyItemViewModel::class.java)



        studyItemAdapter = StudyItemAdapter(ArrayList())
        studyItemViewModel.trickList.observe(this) {
            if(!hasLoadedLit) {
                binding?.recViewStudy?.adapter = studyItemAdapter
                hasLoadedLit = true
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding?.recViewStudy) {
            this?.layoutManager = LinearLayoutManager(context)
            this?.adapter = studyItemAdapter
        }
        binding?.fabStudy?.setOnClickListener{
            showDialogStudy()
        }
    }

    private fun showDialogStudy() {
        val studyExit = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }
        context?.let {
            AlertDialog.Builder(it)
                .setView(studyExit)
                .setTitle("Voeg een tip toe!")
                .setPositiveButton("Create") { dialog, _ ->
                    studyItem = StudyItem(studyExit.text.toString(), 0)

                    //studyItem.writeToDatabase(studyExit.text.toString())
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

}

