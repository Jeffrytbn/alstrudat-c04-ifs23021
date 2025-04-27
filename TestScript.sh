#!/bin/bash

# Hentikan script jika terjadi error (diubah menjadi tidak berhenti)
set +e

# Compile Java program dengan Maven
echo "🔨 Compiling Java program..."
if ! mvn clean package; then
  echo "❌ Build failed! Exiting..."
  exit 1
fi
echo "✅ Build successful!"

# Pastikan file weights.txt ada
if [ ! -f testcases/weights.txt ]; then
  echo "❌ Error: File testcases/weights.txt not found!"
  exit 1
fi

# Variabel untuk menyimpan nilai akhir
total_score=0
index=1

# Baca bobot dari file weights.txt
while IFS= read -r weight; do
  # Bersihkan karakter non-digit dari weight
  clean_weight=$(echo "$weight" | tr -cd '[:digit:]')
  
  echo "|--------------------------------------------------|"
  echo "Test Case $index"
  input_file="testcases/input$index.txt"
  expected_file="testcases/expected$index.txt"
  actual_output_file="testcases/actual_output$index.txt"

  # Pastikan file test case tersedia
  if [ ! -f "$input_file" ] || [ ! -f "$expected_file" ]; then
    echo "⚠️ Warning: Test case $index files missing. Skipping..."
    index=$((index + 1))
    continue
  fi

  echo -e "🚀 Running test case $index (Weight: ${clean_weight}%)..."

  # Jalankan program dengan input dari file dan simpan outputnya
  java -cp target/app.jar del.alstrudat.App < "$input_file" > "$actual_output_file"

  # Bandingkan output dengan expected output
  if diff -q --strip-trailing-cr "$expected_file" "$actual_output_file" > /dev/null; then
    echo "✅ Test case $index passed! (+${clean_weight}%)"
    total_score=$((total_score + clean_weight))
  else
    # Tampilkan isi dari output dan expected output untuk debugging
    echo "🔍 Expected Output (testcase $index):"
    cat "$expected_file"
    echo "🔍 Actual Output (testcase $index):"
    cat "$actual_output_file"

    echo -e "\n❌ Test case $index failed! [Differences below]"
    echo "====================================="
    diff -u --color=always --strip-trailing-cr "$expected_file" "$actual_output_file" | cat -n
    echo "====================================="
  fi

  index=$((index + 1))
done < <(tr -d '\r' < testcases/weights.txt)

echo "|--------------------------------------------------|"
echo "🎯 Final Score: ${total_score}%"

# Jika nilai tidak 100%, buat skrip error
if [ "$total_score" -ne 100 ]; then
  echo "❌ Error: Some test cases failed! Exiting with error."
  exit 1
fi