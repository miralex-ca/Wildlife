package com.muralex.navstructure.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.muralex.navstructure.app.utils.Constants
import com.muralex.navstructure.databinding.FragmentDetailBinding
import com.muralex.navstructure.domain.data.article.DetailArticleUI
import com.muralex.navstructure.presentation.detail.DetailContract.UserAction
import com.muralex.navstructure.presentation.detail.DetailContract.ViewIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservation()
        val sectionID = arguments?.getString(Constants.SECTION_ARG_KEY) ?: ""
        val articleID = arguments?.getString(Constants.ARTICLE_ARG_KEY) ?: ""
        processUiEvent(UserAction.LaunchScreen(articleID, sectionID))

    }



    private fun setUpObservation() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            renderViewState(state)
        }

        viewModel.viewEffect.onEach {
            renderViewEffect(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun processUiEvent(userAction: UserAction) {
        when (userAction) {
            is UserAction.LaunchScreen -> setIntent(ViewIntent.GetArticle(userAction.articleID,
                userAction.sectionID))
            is UserAction.NextButtonClick -> setIntent(ViewIntent.GetPreviousArticle(userAction.nextID,
                userAction.sectionID))
            is UserAction.PreviousButtonClick -> setIntent(ViewIntent.GetPreviousArticle(userAction.previousID,
                userAction.sectionID))
        }
    }

    private fun setIntent(intent: ViewIntent) {
        viewModel.setIntent(intent)
    }

    private fun renderViewState(state: DetailContract.ViewState) {
        when (state) {
            is DetailContract.ViewState.Idle -> {}
            is DetailContract.ViewState.ArticleLoaded -> {
                setToolbarTitle(state.data.article.title)
                refreshUI(state.data)
                setButtonsListeners(state.data)
            }
        }
    }

    private fun setButtonsListeners(data: DetailArticleUI) {

        binding.btnNext.setOnClickListener {
            processUiEvent(UserAction.NextButtonClick(data.nextId, data.sectionId))
        }

        binding.btnPrev.setOnClickListener {
            processUiEvent(UserAction.PreviousButtonClick(data.previousId, data.sectionId))
        }
    }

    private fun renderViewEffect(effect: DetailContract.ViewEffect) {

    }

    private fun refreshUI(data: DetailArticleUI?) {
        binding.article = data
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
    }

    private fun setToolbarTitle(title: String) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}