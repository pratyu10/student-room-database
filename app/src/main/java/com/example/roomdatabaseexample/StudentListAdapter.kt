package com.example.roomdatabaseexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class StudentListAdapter(
    private val clickListener: OnClickListener
    ) : ListAdapter<Student, StudentListAdapter.StudentViewHolder>(StudentsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        return StudentViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_item, parent, false
            )
        )
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textView)

        fun bind(
            student: Student,
            clickListener: OnClickListener
        ) {
            name.text = student.studentName
            name.setOnClickListener{
                clickListener.onStudentItemClick(student)
            }
        }
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student: Student=getItem(position)
        holder.bind(student, clickListener)
    }

    class OnClickListener(val clickListener: (student: Student) -> Unit) {
        fun onStudentItemClick(student: Student) {
            clickListener(student)
        }
    }


    class StudentsComparator : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            return oldItem.studentName == newItem.studentName
        }
    }

}