package daniel.lop.io.marvelappstarter.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import daniel.lop.io.marvelappstarter.R
import daniel.lop.io.marvelappstarter.databinding.FragmentSearchCharacterBinding
import daniel.lop.io.marvelappstarter.ui.adapters.CharacterAdapter
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment
import daniel.lop.io.marvelappstarter.ui.state.ResourceState
import daniel.lop.io.marvelappstarter.util.Constants.DEFAULT_QUERY
import daniel.lop.io.marvelappstarter.util.Constants.LAST_SEARCH_QUERY
import daniel.lop.io.marvelappstarter.util.hide
import daniel.lop.io.marvelappstarter.util.show
import daniel.lop.io.marvelappstarter.util.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchCharacterFragment:
    BaseFragment<FragmentSearchCharacterBinding, SearchCharacterViewModel>() {

    override val viewModel: SearchCharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapter()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        searchInit(query)
        collectObserver()
    }

    //preciso abrir esse scopo de coroutines pra coletar esses dados sem problemas, sem usar o "suspend"
    private fun collectObserver() = lifecycleScope.launch {
        viewModel.searchCharacter.collect{ result ->
            when(result){
                is ResourceState.Success -> {
                    binding.progressbarSearch.hide()
                    result.data?.let {
                        characterAdapter.characters = it.data.results.toList()
                    }
                }

                is ResourceState.Error -> {
                    binding.progressbarSearch.hide()
                    result.message?.let { message ->
                        Timber.tag("SearchCharacterFragment").e("Error -> $message")
                        toast(getString(R.string.an_error_occurred))
                    }
                }

                is ResourceState.Loading -> {
                    binding.progressbarSearch.show()
                }
                else -> {}
            }
        }
    }

    private fun searchInit(query: String) = with(binding){
        edSearchCharacter.setText(query)
        edSearchCharacter.setOnEditorActionListener { _, actionid, _ ->
            if (actionid == EditorInfo.IME_ACTION_GO){
                updateCharacterList()
                true
            }else{
                false
            }
        }

        edSearchCharacter.setOnKeyListener { _, keyCode, event -> //quando usuario clicar em ir, vai pro evento
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                updateCharacterList()
                true
            }else{
                false
            }
        }
    }

    private fun updateCharacterList() = with(binding) {
        edSearchCharacter.editableText.trim().let {
            if (it.isNotEmpty()){
                searchQuery(it.toString())
            }
        }
    }

    private fun searchQuery(query: String) {
        viewModel.fetch(query)
    }

    override fun onSaveInstanceState(outState: Bundle) { //guardar a busca feita pelo usuario
        super.onSaveInstanceState(outState) //caso ele vire o celular, não perde a busca feita
        outState.putString(LAST_SEARCH_QUERY,
        binding.edSearchCharacter.editableText.trim().toString())
    }

    private fun clickAdapter() {
        characterAdapter.setOnClickListener { characterModel ->
            val action = SearchCharacterFragmentDirections
                .actionSearchCharacterFragmentToDetailCharacterFragment(characterModel)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvSearchCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}