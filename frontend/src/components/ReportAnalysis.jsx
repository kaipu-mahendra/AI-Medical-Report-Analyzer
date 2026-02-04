import React from 'react';
import { CheckCircle, AlertTriangle, XCircle, Activity } from 'lucide-react';

const ReportAnalysis = ({ report }) => {
  if (!report) return null;

  const { patientName, reportDate, testResults } = report;

  const getStatusColor = (status) => {
    switch (status?.toUpperCase()) {
      case 'NORMAL': return 'text-green-600 bg-green-50';
      case 'HIGH': return 'text-red-600 bg-red-50';
      case 'LOW': return 'text-yellow-600 bg-yellow-50';
      default: return 'text-gray-600 bg-gray-50';
    }
  };

  const getStatusIcon = (status) => {
    switch (status?.toUpperCase()) {
      case 'NORMAL': return <CheckCircle className="w-4 h-4" />;
      case 'HIGH': return <AlertTriangle className="w-4 h-4" />;
      case 'LOW': return <XCircle className="w-4 h-4" />;
      default: return <Activity className="w-4 h-4" />;
    }
  };

  return (
    <div className="bg-white p-6 rounded-lg shadow-md max-w-4xl mx-auto mt-8 animate-in fade-in slide-in-from-bottom-4 duration-500">
      <div className="flex justify-between items-center mb-6 border-b pb-4">
        <div>
          <h2 className="text-2xl font-bold text-gray-800">Analysis Results</h2>
          <p className="text-gray-500 text-sm">Patient: <span className="font-medium text-gray-700">{patientName}</span></p>
        </div>
        <div className="text-right">
          <p className="text-sm text-gray-500">Date</p>
          <p className="font-medium text-gray-700">{new Date(reportDate).toLocaleDateString()}</p>
        </div>
      </div>

      <div className="overflow-x-auto">
        <table className="w-full text-left border-collapse">
          <thead>
            <tr className="bg-gray-50 text-gray-600 text-sm uppercase tracking-wider">
              <th className="p-3 border-b font-semibold">Test Name</th>
              <th className="p-3 border-b font-semibold">Value</th>
              <th className="p-3 border-b font-semibold">Status</th>
              <th className="p-3 border-b font-semibold">Explanation</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-100">
            {testResults.map((result, index) => (
              <tr key={index} className="hover:bg-gray-50 transition-colors">
                <td className="p-3 font-medium text-gray-800">{result.testName}</td>
                <td className="p-3 text-gray-700">
                  {result.value} <span className="text-xs text-gray-400">{result.unit}</span>
                </td>
                <td className="p-3">
                  <span className={`inline-flex items-center gap-1 px-2.5 py-0.5 rounded-full text-xs font-medium ${getStatusColor(result.status)}`}>
                    {getStatusIcon(result.status)}
                    {result.status}
                  </span>
                </td>
                <td className="p-3 text-sm text-gray-600 max-w-xs">{result.explanation}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {testResults.length === 0 && (
        <div className="text-center py-8 text-gray-500">
          No test results found in this report.
        </div>
      )}
    </div>
  );
};

export default ReportAnalysis;
