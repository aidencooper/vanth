import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
   server: {
    proxy: {
      "/data": {
        target: "http://localhost:8471",
        rewrite: (path) => path.replace(/^\/data/, ""),
      },
      "/media": {
        target: "http://localhost:8470",
        rewrite: (path) => path.replace(/^\/media/, ""),
      },
    },
  },
})
