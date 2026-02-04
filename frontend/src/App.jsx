import React, { useState } from 'react';
import FileUpload from './components/FileUpload';
import ReportAnalysis from './components/ReportAnalysis';
import { Stethoscope, Mail } from 'lucide-react';

function App() {
  const [reportData, setReportData] = useState(null);

  const handleAnalysisComplete = (data) => {
    setReportData(data);
  };

  return (
    <div className="min-h-screen bg-gray-100 font-sans text-gray-900 pb-12">
      {/* Header */}
      <header className="bg-white shadow-sm sticky top-0 z-10">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4 flex items-center gap-3">
          <div className="bg-blue-600 p-2 rounded-lg">
            <Stethoscope className="w-6 h-6 text-white" />
          </div>
          <h1 className="text-2xl font-bold text-gray-800 tracking-tight">
            AI Medical Report Analyzer
          </h1>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 pt-8">
        <div className="text-center mb-10">
          <h2 className="text-3xl font-extrabold text-gray-900 sm:text-4xl">
            Understand Your Lab Results Instantly
          </h2>
          <p className="mt-3 max-w-2xl mx-auto text-xl text-gray-500 sm:mt-4">
            Upload your medical report (PDF or Image) and let our AI extract values, 
            compare them with normal ranges, and provide simple explanations.
          </p>
        </div>

        {/* Upload Section */}
        <div className="mb-12">
          <FileUpload onAnalysisComplete={handleAnalysisComplete} />
        </div>

        {/* Results Section */}
        {reportData && (
          <div id="results">
            <ReportAnalysis report={reportData} />
          </div>
        )}
      </main>

      {/* Footer */}
      <footer className="bg-white border-t mt-12 py-8">
        <div className="max-w-7xl mx-auto px-4 text-center">
          <p className="text-gray-700 font-medium mb-2">
            We crafted with <span className="text-red-500">❤️</span>
          </p>
          <p className="text-gray-600 mb-3">
            Eat well, do well and keep your health in check! Your well-being is our priority.
          </p>
          <p className="text-gray-400 text-sm mb-3">
            © 2026 AI Medical Analyzer. All rights reserved.
          </p>
          <div className="flex justify-center items-center gap-4 flex-wrap">
            <a 
              href="mailto:kaipumahendrar@gmail.com" 
              className="flex items-center gap-2 text-blue-600 hover:text-blue-800 transition-colors"
            >
              <Mail className="w-4 h-4" />
              <span className="text-sm">kaipumahendrar@gmail.com</span>
            </a>
            <a 
              href="mailto:gadugoyyalakesumani@gmail.com" 
              className="flex items-center gap-2 text-blue-600 hover:text-blue-800 transition-colors"
            >
              <Mail className="w-4 h-4" />
              <span className="text-sm">gadugoyyalakesumani@gmail.com</span>
            </a>
          </div>
        </div>
      </footer>
    </div>
  );
}

export default App;
