package com.gokanaz.kanazpad.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokanaz.kanazpad.KanazPadApplication
import com.gokanaz.kanazpad.R
import com.gokanaz.kanazpad.ui.adapter.NoteAdapter
import com.gokanaz.kanazpad.viewmodel.NoteViewModel
import com.gokanaz.kanazpad.viewmodel.NoteViewModelFactory

class MainFragment : Fragment() {

    private val viewModel: NoteViewModel by activityViewModels {
        NoteViewModelFactory((requireActivity().application as KanazPadApplication).repository)
    }

    private lateinit var adapter: NoteAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = NoteAdapter(
            onNoteClick = { note ->
            },
            onMenuClick = { note, view ->
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }
    }
}
