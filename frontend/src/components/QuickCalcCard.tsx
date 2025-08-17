import { useState } from 'react';

export default function QuickCalcCard() {
    const [age, setAge] = useState<string>('');

    const SexOptions = ['Male', 'Female'] as const;
    type Sex = typeof SexOptions[number];
    const [sex, setSex] = useState<Sex>('Male');

    const [egfr, setEgfr] = useState<string>('');
    const [acr, setAcr] = useState<string>('');
    const [result, setResult] = useState<string>('');

    function onCalculate() {
        // placeholder – απλός έλεγχος εισόδου
        if (!age || !egfr || !acr) {
            setResult('Please fill all fields to preview your risk.');
            return;
        }
        setResult('Result will appear here; nothing is stored.');
    }

    return (
        <div className="card">
            <p className="section-title">Quick Calculation</p>
            <p className="muted">Enter your information to calculate your kidney failure risk.</p>

            <div className="form-grid" style={{marginTop:12}}>
                <label>
                    <div className="label">Age (years)</div>
                    <input className="input" placeholder="e.g. 65" inputMode="numeric"
                           value={age} onChange={e=>setAge(e.target.value)} />
                </label>

                <label>
                    <div className="label">Sex</div>
                    <select className="input" value={sex} onChange={e=>setSex(e.target.value as Sex)}>
                        {SexOptions.map(opt => (
                            <option key={opt} value={opt}>{opt}</option>
                        ))}
                    </select>
                </label>

                <label>
                    <div className="label">eGFR (mL/min/1.73m²)</div>
                    <input className="input" placeholder="e.g. 45" inputMode="decimal"
                           value={egfr} onChange={e=>setEgfr(e.target.value)} />
                </label>

                <label>
                    <div className="label">ACR (mg/g)</div>
                    <input className="input" placeholder="e.g. 150" inputMode="decimal"
                           value={acr} onChange={e=>setAcr(e.target.value)} />
                </label>
            </div>

            <div className="form-actions">
                <button className="btn btn-primary" onClick={onCalculate}>Calculate</button>
                <span style={{marginLeft:12}} className="muted">{result || 'No sign-in required. We don’t save anything.'}</span>
            </div>

            <div style={{marginTop:16}}>
                <p className="section-title" style={{fontSize:16}}>For Individuals</p>
                <p className="muted">Instant risk preview. No account required.</p>

                <p className="section-title" style={{fontSize:16, marginTop:8}}>For Doctors</p>
                <p className="muted">Full features after sign-in. Your data stays private.</p>

                <p className="section-title" style={{fontSize:16, marginTop:8}}>Privacy-first</p>
                <p className="muted">Quick calculations never leave your browser.</p>
            </div>
        </div>
    );
}
