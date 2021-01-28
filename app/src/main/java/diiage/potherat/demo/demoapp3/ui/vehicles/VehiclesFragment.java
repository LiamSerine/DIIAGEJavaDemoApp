package diiage.potherat.demo.demoapp3.ui.vehicles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.VehiclesFragmentBinding;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

@AndroidEntryPoint
public class VehiclesFragment extends Fragment {

    @Inject
    public VehiclesViewModel viewmodel;
    private VehiclesFragmentBinding binding;

    private ProgressBar progress;
    private EditText input;
    private TextView text;
    private Button button;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = VehiclesFragmentBinding.inflate(inflater,container,false);

        ready();

        View root = binding.getRoot();

        input = root.findViewById(R.id.input);
        text = root.findViewById(R.id.text);
        button = root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.getText().toString().equals("") && Integer.parseInt(input.getText().toString()) > 0 ) {
                    viewmodel.loadVehicle(Integer.parseInt(input.getText().toString()));
                    viewmodel.getVehicle().observe(getViewLifecycleOwner(), new Observer<Vehicle>() {
                        @Override
                        public void onChanged(Vehicle vehicle) {
                            if ( vehicle == null ) {
                                text.setText("Le véhicule n'existe pas !");
                            } else {
                                text.setText("Nom du véhicule : " + vehicle.name + "\n Modèle du véhicule : " + vehicle.model);
                            }
                        }
                    });
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void ready(){
        if (binding != null && viewmodel != null){
            binding.setLifecycleOwner(this);
            binding.setViewmodel(viewmodel);
        }
    }
}