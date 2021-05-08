package com.fedyshyn.paging.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fedyshyn.paging.R
import com.fedyshyn.paging.model.Repo

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView = view.findViewById(R.id.repo_name)
    private val description: TextView = view.findViewById(R.id.repo_description)
    private val language: TextView = view.findViewById(R.id.repo_language)

    private var repo: Repo? = null

    init {
        view.setOnClickListener {
            repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(repo: Repo?) {
        if (repo == null) {
            description.visibility = View.GONE
            language.visibility = View.GONE
        } else {
            showRepoData(repo)
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun showRepoData(repo: Repo) {
        this.repo = repo
        name.text = repo.fullName

        var descriptionVisibility = View.GONE
        if (repo.description != null) {
            description.text = repo.description
            descriptionVisibility = View.VISIBLE
        }

        description.visibility = descriptionVisibility

        var languageVisibility = View.GONE
        if (!repo.language.isNullOrEmpty()) {
            val resources = this.itemView.context.resources
            language.text = resources.getString(R.string.language, repo.language)
            languageVisibility = View.VISIBLE
        }

        language.visibility = languageVisibility
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_view_item, parent, false)
            return RepoViewHolder(view)
        }
    }
}