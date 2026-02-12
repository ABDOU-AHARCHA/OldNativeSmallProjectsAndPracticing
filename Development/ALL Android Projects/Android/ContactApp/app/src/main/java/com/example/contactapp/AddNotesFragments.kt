package com.example.contactapp

import android.content.ContentValues
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNotesFragments.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNotesFragments : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


//    var EditTitel = view?.findViewById<EditText>(R.id.EditTextTitle)
//    var EditDesc = view?.findViewById<EditText>(R.id.EditTextDesc)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view:View = inflater.inflate(R.layout.fragment_add_notes_fragments, container, false);

        val ButtonAdd = view.findViewById<Button>(R.id.ButtonAddNotes)
        ButtonAdd.setOnClickListener{
            AddNotesEvents()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    fun AddNotesEvents() {
        val title=view?.findViewById<EditText>(R.id.EditTextTitle)!!.text.toString()
        val desc=view?.findViewById<EditText>(R.id.EditTextDesc)!!.text.toString()
        val values=ContentValues()
        values.put("Title",title)
        values.put("Description",desc)

        val dbManger=DbManager(this!!.requireActivity())

        val id= dbManger.insertvalues(values)
        if(id>0){
            Toast.makeText(requireContext(),"Record is added",Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(requireContext(),"Record is failed to added",Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item!!.itemId){
            R.id.BackButton -> {
                requireView().findNavController().navigate(R.id.notesListFragmnents)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater?.inflate(R.menu.addnotes_menu,menu)// you inflate menu(xml file) and themenu which you place your items

        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddNotesFragments.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddNotesFragments().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}