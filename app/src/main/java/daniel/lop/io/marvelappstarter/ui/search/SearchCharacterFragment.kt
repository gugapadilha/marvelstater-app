package daniel.lop.io.marvelappstarter.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import daniel.lop.io.marvelappstarter.databinding.FragmentSearchCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment

class SearchCharacterFragment:
    BaseFragment<FragmentSearchCharacterBinding, SearchCharacterViewModel>() {
    override val viewModel: SearchCharacterViewModel by viewModels()

    override fun getViewBlinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}