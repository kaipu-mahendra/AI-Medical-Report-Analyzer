# AI-Powered Medical Report Analyzer - Deployment Guide

## Architecture
- **Frontend**: React + Vite → Deploy to **Vercel**
- **Backend**: Spring Boot + Java → Deploy to **Render**

---

## Step 1: Deploy Backend to Render

### Option A: Using GitHub (Recommended)

1. **Push your code to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/medical-report-analyzer.git
   git push -u origin main
   ```

2. **Go to [Render.com](https://render.com)** and sign up/login

3. **Create New Web Service**
   - Click "New" → "Web Service"
   - Connect your GitHub repository
   - Configure:
     - **Name**: `medical-report-analyzer-api`
     - **Root Directory**: `backend`
     - **Environment**: `Docker`
     - **Plan**: Free

4. **Add Environment Variables**
   - `SPRING_PROFILES_ACTIVE` = `prod`
   - `JAVA_OPTS` = `-Xmx512m`

5. **Click "Create Web Service"**

6. **Copy the URL** (e.g., `https://medical-report-analyzer-api.onrender.com`)

---

## Step 2: Deploy Frontend to Vercel

1. **Go to [Vercel.com](https://vercel.com)** and sign up/login

2. **Import your GitHub repository**
   - Click "Add New" → "Project"
   - Select your repository

3. **Configure the project**
   - **Framework Preset**: Vite
   - **Root Directory**: `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`

4. **Add Environment Variable**
   - `VITE_API_URL` = `https://your-backend-url.onrender.com` (use your Render URL from Step 1)

5. **Click "Deploy"**

---

## Step 3: Update Frontend Environment

After getting your Render backend URL, update the `.env.production` file:

```env
VITE_API_URL=https://medical-report-analyzer-api.onrender.com
```

Then redeploy the frontend on Vercel.

---

## Alternative: Deploy Both on Render

You can also deploy the frontend as a static site on Render:

1. Create a new **Static Site** on Render
2. Set **Root Directory**: `frontend`
3. Set **Build Command**: `npm install && npm run build`
4. Set **Publish Directory**: `dist`
5. Add environment variable `VITE_API_URL` with your backend URL

---

## Local Development

### Backend
```bash
cd backend
mvn spring-boot:run
```
Backend runs on: http://localhost:8080

### Frontend
```bash
cd frontend
npm install
npm run dev
```
Frontend runs on: http://localhost:5173

---

## Important Notes

1. **Free Tier Limitations**:
   - Render free tier spins down after 15 minutes of inactivity
   - First request after spin-down may take 30-60 seconds

2. **Tesseract OCR**:
   - The Docker image includes Tesseract training data
   - OCR quality depends on image clarity

3. **File Size Limit**:
   - Maximum upload size is 10MB

---

## Troubleshooting

### CORS Errors
- Ensure your backend CORS config includes your frontend URL
- Check that `@CrossOrigin(origins = "*")` is on controllers

### OCR Not Working
- Verify `tessdata` folder is copied in Dockerfile
- Check `TESSDATA_PREFIX` environment variable

### Build Failures
- Ensure Java 17+ is specified in Dockerfile
- Check Maven dependencies are correct
