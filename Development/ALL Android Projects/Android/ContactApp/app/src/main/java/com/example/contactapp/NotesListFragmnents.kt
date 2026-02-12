package com.example.contactapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotesListFragmnents.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotesListFragmnents : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var arrayListNotes= ArrayList<Note>()
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
        return inflater.inflate(R.layout.fragment_notes_list_fragmnents, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        querySearch("%")
        var myadapter= Adapter(this!!.requireActivity(),arrayListNotes)
        view.findViewById<ListView>(R.id.ListViewNotes).adapter=myadapter
        setHasOptionsMenu(true)
    }


    fun querySearch(noteTitle:String){
        var dbManager =DbManager(this!!.requireActivity())
        val projections= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(noteTitle)
        val cursor =dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        if(cursor.moveToFirst()){
            arrayListNotes.clear()
            do{
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val title = cursor.getString(cursor.getColumnIndex("Title"))
                val description = cursor.getString(cursor.getColumnIndex("Description"))
                arrayListNotes.add(Note(id,title,description))

            }while(cursor.moveToNext())
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.AddNotesButton -> {
                requireView().findNavController().navigate(R.id.action_notesListFragmnents_to_addNotesFragments)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater?.inflate(R.menu.menu,menu)// you inflate menu(xml file) and themenu which you place your items

        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotesListFragmnents.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotesListFragmnents().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class Adapter:BaseAdapter{
        var context:Context?=null
        var arraylistAdapter = ArrayList<Note>()
        constructor(context:Context,arraylistAdapter:ArrayList<Note>):super(){
            this.context=context
            this.arraylistAdapter=arraylistAdapter
        }

        override fun getCount(): Int {
            return arraylistAdapter.size
        }

        override fun getItem(position: Int): Any {
            return arraylistAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.notes_ticket,null)
            var myView2 = layoutInflater.inflate(R.layout.fragment_notes_list_fragmnents,null)
            var note = arraylistAdapter[position]
            myView.findViewById<TextView>(R.id.tvTitle).text=note.nodeTitle
            myView.findViewById<TextView>(R.id.tvDes).text=note.nodeDes
            myView.findViewById<ImageView>(R.id.ivDelete).setOnClickListener{
                val dbManager = DbManager(this!!.context!!)
                val selectionArgs =arrayOf(note.nodeID.toString())
                dbManager.Delete("ID=?",selectionArgs)

            }
            return myView
        }
    }
}