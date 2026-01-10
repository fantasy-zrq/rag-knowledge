curl http://111.228.62.219:11434/api/generate \
  -H "Content-Type: application/json" \
  -d '{
        "model": "deepseek-r1:1.5b",
        "prompt": "who are yuo?",
        "stream": false
      }'