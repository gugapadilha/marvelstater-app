package daniel.lop.io.marvelappstarter.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import daniel.lop.io.marvelappstarter.databinding.FragmentSearchCharacterBinding
import daniel.lop.io.marvelappstarter.ui.base.BaseFragment
import daniel.lop.io.marvelappstarter.ui.list.ListCharacterViewModel

class FavoriteCharacterFragment:

    BaseFragment<FragmentSearchCharacterBinding, FavoriteCharacterViewModel>() {

    override val viewModel: ListCharacterViewModel by viewModels()

    override fun getViewBlinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)

}