package com.android.recyclerview.old.views.tab_game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.recyclerview.R;
import com.android.recyclerview.databinding.FragmentGameBinding;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;

    private int idButton = 0;
    private final int[][] idButtonEven = new int[5][5];
    private final int[][] idButtonOdd = new int[5][5];
    private final boolean[][] clicked = new boolean[5][5];

    public static final int EVEN_NUMBER = 2;
    public static final int ODD_NUMBER = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onClickButton();
    }

    /**
     * return 0: even number;
     * return 1: odd number
     */
    private int checkEvenOddNumber() {
        if (idButton % 2 == 0) {
            return 2;
        }
        return 1;
    }

    private boolean saveClicked(int positionRow, int positionColumn) {
        if (!clicked[positionRow][positionColumn]) {
            clicked[positionRow][positionColumn] = true;
            idButton++;

            if (checkEvenOddNumber() == EVEN_NUMBER) {
                idButtonEven[positionRow][positionColumn] = 2;
                if ((idButtonEven[positionRow][positionColumn - 1] == idButtonEven[positionRow][positionColumn])
                        && (idButtonEven[positionRow][positionColumn] == idButtonEven[positionRow][positionColumn + 1])) {
                    Toast.makeText(requireContext(), "You win", Toast.LENGTH_SHORT).show();
                }

            } else if (checkEvenOddNumber() == ODD_NUMBER) {
                idButtonOdd[positionRow][positionColumn] = 1;
                if ((idButtonOdd[positionRow][positionColumn - 1] == idButtonOdd[positionRow][positionColumn])
                        && (idButtonOdd[positionRow][positionColumn] == idButtonOdd[positionRow][positionColumn + 1])) {
                    Toast.makeText(requireContext(), "You win", Toast.LENGTH_SHORT).show();
                }
            }
            return true;

        } else {
            Toast.makeText(requireContext(), "Can't click in here", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void onClickButton() {
        binding.button00.setOnClickListener(view -> {
            if (saveClicked(0, 0)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button00.setText("x");
                    binding.button00.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button00.setText("o");
                    binding.button00.setTextColor(R.color.white);
                }
            }
        });

        binding.button01.setOnClickListener(view -> {
            if (saveClicked(0, 1)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button01.setText("x");
                    binding.button01.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button01.setText("o");
                    binding.button01.setTextColor(R.color.white);
                }
            }
        });

        binding.button02.setOnClickListener(view -> {
            if (saveClicked(0, 2)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button02.setText("x");
                    binding.button02.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button02.setText("o");
                    binding.button02.setTextColor(R.color.white);
                }
            }
        });

        binding.button03.setOnClickListener(view -> {
            if (saveClicked(0, 3)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button03.setText("x");
                    binding.button03.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button03.setText("o");
                    binding.button03.setTextColor(R.color.white);
                }
            }
        });

        binding.button04.setOnClickListener(view -> {
            if (saveClicked(0, 4)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button04.setText("x");
                    binding.button04.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button04.setText("o");
                    binding.button04.setTextColor(R.color.white);
                }
            }
        });

        binding.button10.setOnClickListener(view -> {
            if (saveClicked(1, 0)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button10.setText("x");
                    binding.button10.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button10.setText("o");
                    binding.button10.setTextColor(R.color.white);
                }
            }
        });

        binding.button11.setOnClickListener(view -> {
            if (saveClicked(1, 1)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button11.setText("x");
                    binding.button11.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button11.setText("o");
                    binding.button11.setTextColor(R.color.white);
                }
            }
        });

        binding.button12.setOnClickListener(view -> {
            if (saveClicked(1, 2)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button12.setText("x");
                    binding.button12.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button12.setText("o");
                    binding.button12.setTextColor(R.color.white);
                }
            }
        });

        binding.button13.setOnClickListener(view -> {
            if (saveClicked(1, 3)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button13.setText("x");
                    binding.button13.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button13.setText("o");
                    binding.button13.setTextColor(R.color.white);
                }
            }
        });

        binding.button14.setOnClickListener(view -> {
            if (saveClicked(1, 4)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button14.setText("x");
                    binding.button14.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button14.setText("o");
                    binding.button14.setTextColor(R.color.white);
                }
            }
        });

        binding.button20.setOnClickListener(view -> {
            if (saveClicked(2, 0)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button20.setText("x");
                    binding.button20.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button20.setText("o");
                    binding.button20.setTextColor(R.color.white);
                }
            }
        });

        binding.button21.setOnClickListener(view -> {
            if (saveClicked(2, 1)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button21.setText("x");
                    binding.button21.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button21.setText("o");
                    binding.button21.setTextColor(R.color.white);
                }
            }
        });

        binding.button22.setOnClickListener(view -> {
            if (saveClicked(2, 2)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button22.setText("x");
                    binding.button22.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button22.setText("o");
                    binding.button22.setTextColor(R.color.white);
                }
            }
        });

        binding.button23.setOnClickListener(view -> {
            if (saveClicked(2, 3)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button23.setText("x");
                    binding.button23.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button23.setText("o");
                    binding.button23.setTextColor(R.color.white);
                }
            }
        });

        binding.button24.setOnClickListener(view -> {
            if (saveClicked(2, 4)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button24.setText("x");
                    binding.button24.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button24.setText("o");
                    binding.button24.setTextColor(R.color.white);
                }
            }
        });

        binding.button30.setOnClickListener(view -> {
            if (saveClicked(3, 0)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button30.setText("x");
                    binding.button30.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button30.setText("o");
                    binding.button30.setTextColor(R.color.white);
                }
            }
        });

        binding.button31.setOnClickListener(view -> {
            if (saveClicked(3, 1)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button31.setText("x");
                    binding.button31.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button31.setText("o");
                    binding.button31.setTextColor(R.color.white);
                }
            }
        });

        binding.button32.setOnClickListener(view -> {
            if (saveClicked(3, 2)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button32.setText("x");
                    binding.button32.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button32.setText("o");
                    binding.button32.setTextColor(R.color.white);
                }
            }
        });

        binding.button33.setOnClickListener(view -> {
            if (saveClicked(3, 3)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button33.setText("x");
                    binding.button33.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button33.setText("o");
                    binding.button33.setTextColor(R.color.white);
                }
            }
        });

        binding.button34.setOnClickListener(view -> {
            if (saveClicked(3, 4)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button34.setText("x");
                    binding.button34.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button34.setText("o");
                    binding.button34.setTextColor(R.color.white);
                }
            }
        });

        binding.button40.setOnClickListener(view -> {
            if (saveClicked(4, 0)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button40.setText("x");
                    binding.button40.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button40.setText("o");
                    binding.button40.setTextColor(R.color.white);
                }
            }
        });

        binding.button41.setOnClickListener(view -> {
            if (saveClicked(4, 1)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button41.setText("x");
                    binding.button41.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button41.setText("o");
                    binding.button41.setTextColor(R.color.white);
                }
            }
        });

        binding.button42.setOnClickListener(view -> {
            if (saveClicked(4, 2)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button42.setText("x");
                    binding.button42.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button42.setText("o");
                    binding.button42.setTextColor(R.color.white);
                }
            }
        });

        binding.button43.setOnClickListener(view -> {
            if (saveClicked(4, 3)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button43.setText("x");
                    binding.button43.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button43.setText("o");
                    binding.button43.setTextColor(R.color.white);
                }
            }
        });

        binding.button44.setOnClickListener(view -> {
            if (saveClicked(4, 4)) {
                if (checkEvenOddNumber() == EVEN_NUMBER) {
                    binding.button44.setText("x");
                    binding.button44.setTextColor(R.color.black);
                } else if (checkEvenOddNumber() == ODD_NUMBER) {
                    binding.button44.setText("o");
                    binding.button44.setTextColor(R.color.white);
                }
            }
        });

    }
}
